import org.junit.Test;
import services.date.DateProperties;
import services.Login;
import services.BaseSteps;

import static io.restassured.RestAssured.given;

public class IArchiveBacklogTest {

    @Test
    public void archiveBacklog() throws Exception {
        String idListBacklog = DateProperties.getFile("idListBacklog");

        Login.login();

        BaseSteps.open("Только для образования");

        BaseSteps.isDisplayedCard("Карточка для изучения API");

        given().header("content-type", "application/json")
                .baseUri("https://api.trello.com/1")
                .queryParam("key", "c07319b117a8482513f35d3f97bc7ed1")
                .queryParam("token", "2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217")
                .queryParam("value", "true")
                    .when().put("/lists/" + idListBacklog + "/closed")
                .then().statusCode(200)
                .log()
                .all();

        BaseSteps.waitLoad();
        Login.driver.close();
    }
}
