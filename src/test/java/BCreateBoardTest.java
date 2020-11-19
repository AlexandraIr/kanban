import org.junit.Test;
import services.Login;
import services.BaseSteps;
import services.date.DateProperties;

import static io.restassured.RestAssured.given;

public class BCreateBoardTest {

    @Test
    public void createBoard() throws Exception {
        Login.login();

        String id = given().header("content-type", "application/json")
                .baseUri("https://api.trello.com/1")
                .queryParam("key", "c07319b117a8482513f35d3f97bc7ed1")
                .queryParam("token", "2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217")
                .queryParam("name", "KanbanTool")
                    .when().post("/boards")
                .then().statusCode(200)
                .extract()
                .jsonPath()
                .get("id");

        DateProperties.changeFile("idBoard", id);

        BaseSteps.waitLoad();
        Login.driver.close();
    }
}
