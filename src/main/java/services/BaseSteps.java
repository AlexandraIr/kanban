package services;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BoardPage;
import pages.CardPage;
import pages.MainPage;

import java.util.Optional;

import static io.restassured.RestAssured.given;

public class BaseSteps {
    public static WebDriver driver;

    public static void isDisplayedCard(String name) {
        MainPage.driver
                .findElement(By.xpath("//span[text()='" + name + "']/ancestor::a[contains(@class, 'list-card ')]"))
                .isDisplayed();
    }

    public static void isChecked(String name, String cardName){
        CardPage cardPage = new CardPage(MainPage.driver);
        cardPage.getCardBTN(cardName).click();
        cardPage.getCard().isDisplayed();
        cardPage.getCheckbox(name).isDisplayed();
        cardPage.getClose().click();
    }

    public static void waitLoad() {
        (new WebDriverWait(MainPage.driver, 60)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='chrome-container']")));
    }

    public static void open(String name) {
        waitLoad();
        WebElement element = MainPage.driver.findElement(By.xpath("//div[@title='" + name + "']/parent::div"));
        element.isDisplayed();
        element.click();
        waitLoad();
    }

    public static void close() {
        String idBoard = DateProperties.getProperty("idBoard");

        given().header("Accept", "application/json")
                .when().delete("https://api.trello.com/1/boards/" + idBoard +
                "?key=c07319b117a8482513f35d3f97bc7ed1" +
                "&token=2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217")
                .then().statusCode(200)
                .log()
                .all();

        MainPage.driver.findElement(By.xpath("//span[@aria-label='HouseIcon']")).click();
        waitLoad();
        MainPage.driver.findElement(By.xpath("//span[text()='Настройки']")).click();
        waitLoad();
        MainPage.driver.findElement(By.xpath("//span[text()='Удалить команду']")).click();
        MainPage.driver.findElement(By.xpath("//input[@value='Удалить навсегда']")).click();

        MainPage.driver.close();
    }

    public static void changeBackgroundToGreen() {
        waitLoad();
        BoardPage boardPage = new BoardPage(MainPage.driver);
        if (boardPage.getButtonMenu().isDisplayed())
            boardPage.getButtonMenu().click();
        boardPage.getBTNBackground().click();
        boardPage.getColor().click();
        Optional<WebElement> element = boardPage.getListColors().stream().filter(c -> c.getAttribute("style").equals("background-color: rgb(75, 191, 107);")).findFirst();
        if (element.isPresent())
        element.get().click();
        else System.exit(1);
        waitLoad();
    }

    public static void makeCommand(){
        BoardPage boardPage = new BoardPage(MainPage.driver);
        boardPage.getPermissionsLevel().click();
        boardPage.setCreateCommand();
    }

    public static void changeNames(){
        BoardPage boardPage = new BoardPage(MainPage.driver);
        boardPage.getNameBoard().click();
        boardPage.getChangeNameBoard().sendKeys("Только для образования");
    }

    public static void cardIsLocatedIn(String nameCard, String nameList){
        try {MainPage.driver.findElement(By.xpath("//h2[text()='" + nameList + "']/ancestor::div[@class = 'list js-list-content']//span[text()='" + nameCard + "']")).isDisplayed();
            System.out.println("Карточка " + nameCard + "находится в колонке " + nameList + ".");
        } catch (NoSuchElementException e) {
            System.out.println("Карта не в требуемой колонке!");
            System.exit(1);
        }
    }

    public static void taskCompletedOnTime(String nameCard){
        CardPage cardPage = new CardPage(MainPage.driver);
        try {
            cardPage.getCardBTN(nameCard).click();
            cardPage.getCard().isDisplayed();
            try {
                cardPage.getTerm().isDisplayed();
            } catch (ElementNotVisibleException e) {
                System.out.println("Срок выполнения задачи исётк.");
                System.exit(1);
        }
            cardPage.checkboxClick();
            cardPage.getClose().click();
        } catch (ElementNotVisibleException e) {
            System.out.println("Карточка не открылась.");
            System.exit(1);
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG (WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
