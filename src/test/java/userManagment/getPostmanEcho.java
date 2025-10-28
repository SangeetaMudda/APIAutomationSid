package userManagment;

import core.StatusCode;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.JsonReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class getPostmanEcho {
    @Test
    public void testDataValidationFromJson() throws IOException, ParseException {
        String username = JsonReader.getTestData("username");
        String password = JsonReader.getTestData("password");
        System.out.println("Username" + username + "Password" + password);
        Response response = given()
                .auth()
                .basic(username, password)
                .when()
                .get("https://postman-echo.com/basic-auth");
        int actualStatusCode = response.statusCode();
        assertEquals(actualStatusCode, StatusCode.SUCCESS.code);
        System.out.println("Executed testDataValidationFromJson");
    }
    @Test(groups = "RegressionSuite")
    public void testAuthValidation() {
        Response response = given()
                .auth()
                .basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth");
        int actualStatusCode = response.statusCode();
        assertEquals(actualStatusCode, StatusCode.SUCCESS.code);
        System.out.println(response.body().asString());
    }

}
