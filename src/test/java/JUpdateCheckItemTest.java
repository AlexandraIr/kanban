import services.DateProperties;
import org.junit.Test;
import services.ALoginTest;
import services.BaseSteps;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class JUpdateCheckItemTest {

    @Test
    public void updateCheckItem() throws SQLException {
        String idCard = DateProperties.getProperty("idCard");
        String idCheckItemTwo = DateProperties.getProperty("idCheckItemTwo");

        ALoginTest.login();
        BaseSteps.saveScreenshotPNG(ALoginTest.driver);
        BaseSteps.open("Только для образования");
        BaseSteps.saveScreenshotPNG(ALoginTest.driver);
        BaseSteps.isDisplayedCard("Карточка для изучения API");

        given().header("content-type", "application/json")
                .when().put("https://api.trello.com/1/cards/" + idCard + "/checkItem/" + idCheckItemTwo +
                "?key=c07319b117a8482513f35d3f97bc7ed1" +
                "&token=2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217" +
                "&state=complete")
                .then().statusCode(200)
                .log()
                .all();

        BaseSteps.isChecked("Понять протокол HTTP", "Карточка для изучения API");
        BaseSteps.isChecked("Выучить методы запросов", "Карточка для изучения API");
        BaseSteps.waitLoad();
        ALoginTest.driver.close();
    }
}
