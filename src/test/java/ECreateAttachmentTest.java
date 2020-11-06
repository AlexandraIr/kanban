import services.BaseSteps;
import services.DateProperties;
import org.junit.Test;
import services.ALoginTest;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class ECreateAttachmentTest {
    public static String idCard = DateProperties.getProperty("idCard");
    public static String pathFile = "src/main/resources/image/image.jpg";

    @Test
    public void createAttachment() throws SQLException {
        ALoginTest.login();

        given().header("content-type", "application/json")
                .when().post("https://api.trello.com/1/cards/" + idCard + "/attachments" +
                        "?key=c07319b117a8482513f35d3f97bc7ed1" +
                        "&token=2aee767d6f0b111f5f1d44c2501d3bbf4666855d9b8a9380fc0cf669c4c1a217" +
                        "&file=" + pathFile)
                .then().statusCode(200)
                .log()
                .all();

        BaseSteps.waitLoad();
        ALoginTest.driver.close();
    }
}
