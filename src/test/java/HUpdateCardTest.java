import services.date.DateProperties;
import org.junit.Test;
import services.Login;
import services.BaseSteps;

import static io.restassured.RestAssured.given;

public class HUpdateCardTest {

    @Test
    public void updateCard() throws Exception {
        String idCard = DateProperties.getFile("idCard");
        String idListDone = DateProperties.getFile("idListDone");

        Login.login();

        given().header("content-type", "application/json")
                .baseUri("https://api.trello.com/1")
                .queryParam("key", "c07319b117a8482513f35d3f97bc7ed1")
                .queryParam("token", "2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217")
                .queryParam("idList", idListDone)
                .when().put("/cards/" + idCard)
                .then().statusCode(200)
                .log()
                .all();

        BaseSteps.open("KanbanTool");
        BaseSteps.isDisplayedCard("Карточка для изучения API");
        BaseSteps.cardIsLocatedIn("Карточка для изучения API", "Done");
        BaseSteps.changeNames("Только для образования");
        BaseSteps.changeBackgroundToGreen();
        BaseSteps.makeCommand();

        BaseSteps.waitLoad();
        Login.driver.close();
    }
}
