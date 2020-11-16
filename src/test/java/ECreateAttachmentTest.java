import org.junit.Test;
import services.ALoginTest;
import services.BaseSteps;
import services.DateProperties;

import static io.restassured.RestAssured.given;

public class ECreateAttachmentTest {

    @Test
    public void createAttachment() throws Exception {
        String idCard = DateProperties.getFile("idCard");
        String pathFile = "src/main/resources/image/image.jpg";

        ALoginTest.login();

        given().header("content-type", "application/json")
                .baseUri("https://api.trello.com/1")
                .queryParam("key", "c07319b117a8482513f35d3f97bc7ed1")
                .queryParam("token", "2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217")
                .queryParam("file", pathFile)
                    .when().post("/cards/" + idCard + "/attachments")
                .then().statusCode(200)
                .log()
                .all();

        BaseSteps.waitLoad();
        ALoginTest.driver.close();
    }
}
