package scnario;

import org.example.DateProperties;
import org.junit.Test;
import steps.ALoginTest;
import steps.BaseSteps;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class FCheckList {
    public static String idCard = DateProperties.getProperty("idCard");

    @Test
    public void checkList() throws SQLException {
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

        DateProperties.setProperty("idChecklist", id);
        DateProperties.setProperty("idCheckItemOne", idCheckItemOne);
        DateProperties.setProperty("idCheckItemTwo", idCheckItemTwo);

        ALoginTest.driver.close();

    }
}
