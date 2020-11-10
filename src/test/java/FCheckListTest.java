import org.junit.Test;
import services.ALoginTest;
import services.BaseSteps;
import services.DateProperties;

import static io.restassured.RestAssured.given;

public class FCheckListTest {

    @Test
    public void checkList() throws Exception {
        String idCard = DateProperties.getFile("idCard");

        ALoginTest.login();
        BaseSteps.open("KanbanTool");
        BaseSteps.isDisplayedCard("Карточка для изучения API");

        String id = given().header("content-type", "application/json")
                .when().post("https://api.trello.com/1/cards/" + idCard + "/checklists" +
                        "?key=c07319b117a8482513f35d3f97bc7ed1" +
                        "&token=2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217" +
                        "&name=Прогресс")
                .then().statusCode(200)
                .extract()
                .jsonPath()
                .get("id");

        String idCheckItemOne = given().header("content-type", "application/json")
                .when().post("https://api.trello.com/1/checklists/"+ id + "/checkItems" +
                "?key=c07319b117a8482513f35d3f97bc7ed1" +
                "&token=2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217" +
                "&name=Понять протокол HTTP")
                .then().statusCode(200)
                .extract()
                .jsonPath()
                .get("id");

        String idCheckItemTwo = given().header("content-type", "application/json")
                .when().post("https://api.trello.com/1/checklists/"+ id + "/checkItems" +
                        "?key=c07319b117a8482513f35d3f97bc7ed1" +
                        "&token=2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217" +
                        "&name=Выучить методы запросов")
                .then().statusCode(200)
                .extract()
                .jsonPath()
                .get("id");

        DateProperties.changeFile("idCheckItemOne", idCheckItemOne);
        DateProperties.changeFile("idCheckItemTwo", idCheckItemTwo);

        ALoginTest.driver.close();

    }
}
