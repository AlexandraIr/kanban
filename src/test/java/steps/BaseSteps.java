package steps;

import org.example.DateProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.services.MainPage;

import static io.restassured.RestAssured.given;

public class BaseSteps {

    public static boolean isDisplayedCard(String name) {
        return MainPage.driver
                .findElement(By.xpath("//span[text()='" + name + "']/ancestor::a[contains(@class, 'list-card ')]"))
                .isDisplayed();
    }

    public static boolean isChecked(){
        return true;
    }

    public static void waitLoad() {
        (new WebDriverWait(MainPage.driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='chrome-container']")));
    }

    public static void open(String name) {
        waitLoad();
        WebElement element = MainPage.driver.findElement(By.xpath("//div[@title='" + name + "']/parent::div"));
        element.isDisplayed();
        element.click();
        waitLoad();
    }

    public static void close() {
        String idCard = DateProperties.getProperty("idCard");
        given().header("Accept", "application/json")
                .when().delete("https://api.trello.com/1/boards/" + idCard +
                "?key=c07319b117a8482513f35d3f97bc7ed1" +
                "&token=2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217")
                .asString();

        MainPage.driver.close();
    }
}
