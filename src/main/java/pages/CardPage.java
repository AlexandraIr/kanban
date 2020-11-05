package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CardPage {
    public static WebDriver driver;

    public CardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        CardPage.driver = driver;
    }

    @FindBy(xpath = "//span[text()='Карточка для изучения API']/ancestor::a")
    WebElement cardBTN;

    @FindBy(xpath = "//h2[text()='Карточка для изучения API']/ancestor::div[contains(@class, 'card-detail-window')]")
    WebElement card;

    @FindBy(xpath = "//a[contains(@class, 'js-close-window')]")
    WebElement close;

    public WebElement getCardBTN() {
        return cardBTN;
    }

    public WebElement getCard() {
        return card;
    }

    public WebElement getClose() {
        return close;
    }

    public WebElement getCheckbox(String name) {
        return driver.findElement(By.xpath("//span[text()='" + name + "']/ancestor::div[contains(@class, 'checklist-item-state-complete')]"));
    }
}
