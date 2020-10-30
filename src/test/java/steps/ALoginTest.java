package steps;

import org.example.ConfProperties;
import org.example.GetSQLPassword;
import org.example.Secure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.services.LoginPage;
import pages.services.MainPage;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static org.example.Secure.ScannerPassword;

public class ALoginTest {
    public static LoginPage loginPage;
    public static MainPage mainPage;
    public static WebDriver driver;

    public ALoginTest(){}

    public static boolean pass() throws SQLException {
        return Secure.getHash().equals(GetSQLPassword.getPassword());
    }

    public static void login() throws SQLException {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("loginpage"));

        if (pass()) {
            loginPage.inputLogin("test.testov_2000@mail.ru", ScannerPassword());
            BaseSteps.waitLoad();
            mainPage.getMainPage().isDisplayed();
        }
    }
}
