import org.junit.Test;
import services.Login;
import services.BaseSteps;
import services.date.DateProperties;
import services.FormatStringDate;

import static io.restassured.RestAssured.given;

public class DAddCardTest {

    @Test
    public void addCard() throws Exception {
        String idList = DateProperties.getFile("idListBacklog");

        Login.login();

        String id = given().header("content-type", "application/json")
                .baseUri("https://api.trello.com/1")
                .queryParam("key", "c07319b117a8482513f35d3f97bc7ed1")
                .queryParam("token", "2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217")
                .queryParam("name", "Карточка для изучения API")
                .queryParam("idList", idList)
                .queryParam("desc", "Тут будет отмечаться прогресс обучения")
                .queryParam("due", FormatStringDate.date())
                    .when().post("/cards")
                .then().statusCode(200)
                .extract()
                .jsonPath()
                .get("id");

        DateProperties.changeFile("idCard", id);

        BaseSteps.open("KanbanTool");
        BaseSteps.isDisplayedCard("Карточка для изучения API");

        BaseSteps.waitLoad();
        Login.driver.close();
    }
}
