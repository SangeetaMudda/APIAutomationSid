package userManagment;

import core.BaseTest;
import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;



public class APIChaining extends BaseTest {


    public String generateAuthToken() throws InterruptedException {

        Response response= RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"userName\":\"SangeetaMuddaAPI\",\"password\":\"Test@123\"}")
                .when()
                .post("https://bookstore.toolsqa.com/Account/v1/GenerateToken");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("generateAuthToken Test case passed");
        //System.out.println(response.getBody().asString());
        String getAuthToken = response.path("token");
        return getAuthToken;

    }

    @Test(groups = "RegressionSuite")
    public void getBooksList() throws InterruptedException {

        Response response= RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + generateAuthToken())
                .body("{\"userId\":\"b20f0798-e83e-455e-b088-2a82cad7d8d1\",\"collectionOfIsbns\":[{\"isbn\":\"9781449325862\"}]}")
                .when()
                .post("https://bookstore.toolsqa.com/BookStore/v1/Books");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("generateAuthToken Test case passed");
        //System.out.println(response.getBody().asString());

    }


}
