import services.DateProperties;
import org.junit.Test;
import services.ALoginTest;
import services.BaseSteps;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class HUpdateCardTest {

    @Test
    public void updateCard() throws SQLException {
        String idCard = DateProperties.getProperty("idCard");
        String idListDone = DateProperties.getProperty("idListDone");

        ALoginTest.login();
        BaseSteps.open("KanbanTool");
        BaseSteps.isDisplayedCard("Карточка для изучения API");

        given().header("content-type", "application/json")
                .when().put("https://api.trello.com/1/cards/" + idCard +
                "?key=c07319b117a8482513f35d3f97bc7ed1" +
                "&token=2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217" +
                "&idList=" + idListDone)
                .then().statusCode(200)
                .log()
                .all();

        BaseSteps.cardIsLocatedIn("Карточка для изучения API", "Done");
        BaseSteps.changeNames();
        BaseSteps.changeBackgroundToGreen();
        BaseSteps.makeCommand();

        BaseSteps.waitLoad();
        ALoginTest.driver.close();
    }
}
