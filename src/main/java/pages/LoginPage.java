package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    public static WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        LoginPage.driver = driver;
    }

    @FindBy(xpath = "//section[@class='inner-section']")
    private WebElement loginPage;

    @FindBy(xpath = "//input[@id='user']")
    private WebElement login;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='login']")
    private WebElement btnLogin;

    @FindBy(xpath = "//button[@id='login-submit']")
    private  WebElement loginSubmit;

    public void inputLogin(String email, String pass) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user']")));
        loginPage.isDisplayed();
        login.sendKeys(email);
        btnLogin.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        password.sendKeys(pass);
        loginSubmit.click();
    }
}