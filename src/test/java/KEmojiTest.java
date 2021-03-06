import org.junit.Test;
import services.Login;
import services.BaseSteps;
import services.date.DateProperties;

import static io.restassured.RestAssured.given;

public class KEmojiTest {

    @Test
    public void emoji() throws Exception {
        String idCard = DateProperties.getFile("idCard");

        Login.login();

        BaseSteps.open("Только для образования");

        BaseSteps.isDisplayedCard("Карточка для изучения API");

        given().header("content-type", "application/json")
                .baseUri("https://api.trello.com/1")
                .queryParam("key", "c07319b117a8482513f35d3f97bc7ed1")
                .queryParam("token", "2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217")
                .queryParam("text", ":thumbsup: ")
                    .when().post("/cards/" + idCard + "/actions/comments")
                .then().statusCode(200)
                .log()
                .all();

        BaseSteps.taskCompletedOnTime("Карточка для изучения API");
        BaseSteps.close();
    }
}
