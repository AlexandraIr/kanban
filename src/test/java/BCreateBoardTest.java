import org.junit.Test;
import services.ALoginTest;
import services.BaseSteps;
import services.DateProperties;

import static io.restassured.RestAssured.given;

public class BCreateBoardTest {

    @Test
    public void createBoard() throws Exception {
        ALoginTest.login();

        String id = given().header("content-type", "application/json")
                .when().post("https://api.trello.com/1/boards" +
                        "?key=c07319b117a8482513f35d3f97bc7ed1" +
                        "&token=2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217" +
                        "&name=KanbanTool")
                .then().statusCode(200)
                .extract()
                .jsonPath()
                .get("id");

        DateProperties.changeFile("idBoard", id);

        BaseSteps.waitLoad();
        ALoginTest.driver.close();
    }
}
