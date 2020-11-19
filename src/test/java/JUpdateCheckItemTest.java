import org.junit.Test;
import services.Login;
import services.BaseSteps;
import services.date.DateProperties;

import static io.restassured.RestAssured.given;

public class JUpdateCheckItemTest {

    @Test
    public void updateCheckItem() throws Exception {
        String idCard = DateProperties.getFile("idCard");
        String idCheckItemTwo = DateProperties.getFile("idCheckItemTwo");

        Login.login();
        BaseSteps.open("Только для образования");
        BaseSteps.isDisplayedCard("Карточка для изучения API");

        given().header("content-type", "application/json")
                .baseUri("https://api.trello.com/1")
                .queryParam("key", "c07319b117a8482513f35d3f97bc7ed1")
                .queryParam("token", "2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217")
                .queryParam("state", "complete")
                    .when().put("/cards/" + idCard + "/checkItem/" + idCheckItemTwo)
                .then().statusCode(200)
                .log()
                .all();

        BaseSteps.isChecked("Понять протокол HTTP", "Карточка для изучения API");
        BaseSteps.isChecked("Выучить методы запросов", "Карточка для изучения API");
        BaseSteps.waitLoad();
        Login.driver.close();
    }
}
