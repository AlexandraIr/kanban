import org.junit.Test;
import services.ALoginTest;
import services.BaseSteps;
import services.DateProperties;

import static io.restassured.RestAssured.given;

public class KEmojiTest {

    @Test
    public void emoji() throws Exception {
        String idCard = DateProperties.getFile("idCard");

        ALoginTest.login();

        BaseSteps.open("Только для образования");

        BaseSteps.isDisplayedCard("Карточка для изучения API");

        given().header("content-type", "application/json")
                .when().post("https://api.trello.com/1/cards/" + idCard + "/actions/comments" +
                "?key=c07319b117a8482513f35d3f97bc7ed1" +
                "&token=2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217" +
                "&text=:thumbsup: ")
                .then().statusCode(200)
                .log()
                .all();

        BaseSteps.taskCompletedOnTime("Карточка для изучения API");
        BaseSteps.close();
    }
}
