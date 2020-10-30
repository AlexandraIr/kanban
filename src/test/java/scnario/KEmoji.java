package scnario;

import org.example.DateProperties;
import org.junit.Test;
import steps.ALoginTest;
import steps.BaseSteps;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class KEmoji {
    public static String idCard = DateProperties.getProperty("idCard");

    @Test
    public void emoji() throws SQLException {
        ALoginTest.login();
        BaseSteps.open("KanbanTool");
        BaseSteps.isDisplayedCard("Карточка для изучения API");

        given().header("content-type", "application/json")
                .when().post("https://api.trello.com/1/cards/" + idCard + "/actions/comments" +
                "?key=c07319b117a8482513f35d3f97bc7ed1" +
                "&token=2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217" +
                "&text=:thumbsup: ")
                .then().statusCode(200)
                .extract()
                .jsonPath()
                .get("id");

        BaseSteps.close();
    }
}
