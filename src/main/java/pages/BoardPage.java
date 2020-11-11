package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;

public class BoardPage {
    public static WebDriver driver;

    public BoardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        BoardPage.driver = driver;
    }

    @FindBy(xpath = "//a[contains(text(), 'Сменить фон')]")
    private WebElement btnBackground;

    @FindBy(xpath = "//div[text()='Цвета']/parent::div")
    private WebElement color;

    @FindBy(xpath = "//div[@class='board-backgrounds-section-tile']/div")
    private List<WebElement> colors;

    @FindBy(xpath = "//a[@id='permission-level']")
    private WebElement permissions;

    @FindBy(xpath = "//a[@name='org']/span[@class='sub-name']")
    private WebElement command;

    @FindBy(xpath = "//span[text()='Меню']/parent::a")
    private WebElement menu;

    @FindBy(xpath = "//a[text()='Создать команду']")
    private WebElement createCommand;

    @FindBy(xpath = "//div[@class='pop-over is-shown']")
    private WebElement createCommandPopup;

    @FindBy(xpath = "//input[@name='displayName']")
    private WebElement inputName;

    @FindBy(xpath = "//div[text()='Выбрать…']")
    private WebElement btnType;

    @FindBy(xpath = "//div[@class='css-11unzgr']//li")
    private List<WebElement> types;

    @FindBy(xpath = "//input[@value='Создать']")
    private WebElement btnCreate;

    @FindBy(xpath = "//h1[@dir='auto']")
    private WebElement nameBoard;

    @FindBy(xpath = "//input[@dir='auto' and contains(@class, 'board')]")
    private WebElement changeNameBoard;

    @FindBy(xpath = "//a[@data-boardtype='Free']")
    private WebElement nameCommand;

    @FindBy(xpath = "//a[text()='Сменить команду…']")
    private WebElement changeCommand;

    public WebElement getBTNBackground() {
        return btnBackground;
    }

    public WebElement getColor() {
        return color;
    }

    public List<WebElement> getListColors() {
        return colors;
    }

    public WebElement getButtonMenu() {
        return menu;
    }

    public WebElement getNameBoard() {
        return nameBoard;
    }

    public WebElement getChangeNameBoard() {
        return changeNameBoard;
    }

    public void setCreateCommand() {
        permissions.click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@name='org']")));
        try {
            command.isDisplayed();
        } catch (StaleElementReferenceException e){
            command.click();
        }

        if (nameCommand.isDisplayed()) {
            nameCommand.click();
            changeCommand.click();
        }

        createCommand.click();
        createCommandPopup.isDisplayed();
        inputName.sendKeys("test");
        btnType.click();
        Optional<WebElement> element = types.stream().filter(c -> c.getText().equals("Образование")).findFirst();
        if (element.isPresent())
            element.get().click();
        else System.exit(1);
        btnCreate.click();
    }

    public void sort() {
        int[] mas = {1,3,77,2,88,9};
        int buf;
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i< mas.length - 1; i ++) {
                if (mas[i] > mas[i+1]) {
                    isSorted = false;

                    buf = mas[i];
                    mas[i] = mas[i + 1];
                    mas[i + 1] = buf;
                }
            }
        }
    }

}
