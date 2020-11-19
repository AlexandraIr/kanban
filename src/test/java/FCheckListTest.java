import org.junit.Test;
import services.Login;
import services.BaseSteps;
import services.date.DateProperties;

import static io.restassured.RestAssured.given;

public class FCheckListTest {

    @Test
    public void checkList() throws Exception {
        String idCard = DateProperties.getFile("idCard");

        Login.login();
        BaseSteps.open("KanbanTool");
        BaseSteps.isDisplayedCard("Карточка для изучения API");

        String id =  given().header("content-type", "application/json")
                .baseUri("https://api.trello.com/1")
                .queryParam("key", "c07319b117a8482513f35d3f97bc7ed1")
                .queryParam("token", "2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217")
                .queryParam("name", "Прогресс")
                    .when().post("/cards/" + idCard + "/checklists")
                .then().statusCode(200)
                .extract()
                .jsonPath()
                .get("id");

        String idCheckItemOne =  given().header("content-type", "application/json")
                .baseUri("https://api.trello.com/1")
                .queryParam("key", "c07319b117a8482513f35d3f97bc7ed1")
                .queryParam("token", "2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217")
                .queryParam("name", "Понять протокол HTTP")
                    .when().post("/checklists/"+ id + "/checkItems")
                .then().statusCode(200)
                .extract()
                .jsonPath()
                .get("id");

        String idCheckItemTwo =  given().header("content-type", "application/json")
                .baseUri("https://api.trello.com/1")
                .queryParam("key", "c07319b117a8482513f35d3f97bc7ed1")
                .queryParam("token", "2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217")
                .queryParam("name", "Выучить методы запросов")
                    .when().post("/checklists/"+ id + "/checkItems")
                .then().statusCode(200)
                .extract()
                .jsonPath()
                .get("id");

        DateProperties.changeFile("idCheckItemOne", idCheckItemOne);
        DateProperties.changeFile("idCheckItemTwo", idCheckItemTwo);

        Login.driver.close();

    }
}
