package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;

public class BoardPage {
    public static WebDriver driver;

    public BoardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        BoardPage.driver = driver;
    }

    @FindBy(xpath = "//a[contains(text(), 'Сменить фон')]")
    WebElement btnBackground;

    @FindBy(xpath = "//div[text()='Цвета']/parent::div")
    WebElement color;

    @FindBy(xpath = "//div[@class='board-backgrounds-section-tile']/div")
    List<WebElement> colors;

    @FindBy(xpath = "//a[@id='permission-level']")
    WebElement permissions;

    @FindBy(xpath = "//a[@name='org']")
    WebElement command;

    @FindBy(xpath = "//span[text()='Меню']/parent::a")
    WebElement menu;

    @FindBy(xpath = "//a[text()='Создать команду']")
    WebElement createCommand;

    @FindBy(xpath = "//div[@class='pop-over is-shown']")
    WebElement createCommandPopup;

    @FindBy(xpath = "//input[@name='displayName']")
    WebElement inputName;

    @FindBy(xpath = "//div[text()='Выбрать…']")
    WebElement btnType;

    @FindBy(xpath = "//div[@class='css-11unzgr']//li")
    List<WebElement> types;

    @FindBy(xpath = "//input[@value='Создать']")
    WebElement btnCreate;

    @FindBy(xpath = "//h1[@dir='auto']")
    WebElement nameBoard;

    @FindBy(xpath = "//input[@dir='auto' and contains(@class, 'board')]")
    WebElement changeNameBoard;

    public WebElement getBTNBackground() {
        return btnBackground;
    }

    public WebElement getColor() {
        return color;
    }

    public List<WebElement> getListColors() {
        return colors;
    }

    public WebElement getPermissionsLevel() {
        return permissions;
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
        command.click();
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
}