package userManagment;

import core.StatusCode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.SoftAssertionUtil;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.testng.Assert.assertEquals;

public class AutomateChatGPTAPI {


    @Test(description = "Validate the list of modules in chatgpt")
    public void getModleslist() {
        Response response = given()
                .header("Authorization", "sdsdsdsa")
                .when().get("https://api.openai.com/v1/models");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.getBody().asString());
        System.out.println("getModleslist validated");


    }
    @Test(description = "Validate the list of modules retrive in chatgpt")
    public void getRetriveModels() {
        Response response = given()
                .header("Authorization", "sdasdasdsd")
                .pathParam("moduleId", "gpt-4o")
                .when().get("https://api.openai.com/v1/models/:moduleId");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.getBody().asString());
        System.out.println("getModleslist validated");


    }



}
