import services.DateProperties;
import org.junit.Test;
import services.ALoginTest;
import services.BaseSteps;
import services.FormatStringDate;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class DAddCard {
    public static String idList = DateProperties.getProperty("idListBacklog");

    @Test
    public void addCard() throws SQLException {
        ALoginTest.login();

        String id = given().header("content-type", "application/json")
                .when().post("https://api.trello.com/1/cards" +
                        "?key=c07319b117a8482513f35d3f97bc7ed1" +
                        "&token=2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217" +
                        "&name=Карточка для изучения API" +
                        "&idList=" + idList +
                        "&desc=Тут будет отмечаться прогресс обучения" +
                        "&due=" + FormatStringDate.date())
                .then().statusCode(200)
                .extract()
                .jsonPath()
                .get("id");

        DateProperties.setProperty("idCard", id);

        BaseSteps.open("KanbanTool");
        BaseSteps.isDisplayedCard("Карточка для изучения API");

        ALoginTest.driver.close();
    }
}
