import org.junit.Test;
import services.ALoginTest;
import services.BaseSteps;
import services.DateProperties;

import static io.restassured.RestAssured.given;

public class CCreateListTest {

    @Test
    public void createList() throws Exception {
        ALoginTest.login();
        String idBoard = DateProperties.getFile("idBoard");

        String idBacklog = given().header("content-type", "application/json")
                .baseUri("https://api.trello.com/1")
                .queryParam("key", "c07319b117a8482513f35d3f97bc7ed1")
                .queryParam("token", "2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217")
                .queryParam("name", "Backlog")
                .queryParam("idBoard", idBoard)
                    .when().post("/lists")
                .then().statusCode(200)
                .extract()
                .jsonPath()
                .get("id");

        String idDone = given().header("content-type", "application/json")
                .when().post("https://api.trello.com/1/lists" +
                        "?key=c07319b117a8482513f35d3f97bc7ed1" +
                        "&token=2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217" +
                        "&name=Done" +
                        "&idBoard=" + idBoard)
                .then().statusCode(200)
                .extract()
                .jsonPath()
                .get("id");

        DateProperties.changeFile("idListBacklog", idBacklog);
        DateProperties.changeFile("idListDone", idDone);

        BaseSteps.waitLoad();
        ALoginTest.driver.close();

    }
}
