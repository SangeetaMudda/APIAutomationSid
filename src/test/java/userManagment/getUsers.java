package userManagment;

import core.BaseTest;
import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.ExtentReport;
import utils.JsonReader;
import utils.PropertyReader;
import utils.SoftAssertionUtil;

import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.Assert.assertEquals;
import static utils.JsonReader.getJsonArray;


public class getUsers extends BaseTest {

    //SoftAssertionUtil softAssertionUtil = new SoftAssertionUtil();

    @Test
    public void getUsersData() {
        given().
                when().get("https://reqres.in/api/users?page=2").
                then().assertThat().statusCode(200);

    }

    @Test
    public void validateGetResponseBody() {
        //set the base uri
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        given()
                .when().get("/todos/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body(not(isEmptyString()))
                .body("title", equalTo("delectus aut autem"))
                .body("userId", equalTo(1));

    }

    @Test
    public void validateResponseHasItems() {
        //set the base uri
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given()
                .when().get("/posts")
                .then()
                .extract()
                .response();

        // use hamcrest to check that the response body contains specific items.

        assertThat(response.jsonPath().getList("title"), hasItems("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "qui est esse"));
    }

    @Test
    public void validateResponseHasSize() {
        //set the base uri
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given()
                .when().get("/comments")
                .then()
                .extract()
                .response();

        // use hamcrest to check that the response body contains specific items.

        assertThat(response.jsonPath().getList(""), hasSize(500));
    }

    @Test
    public void validateListContainsInOrder() {
        //set the base uri
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given()
                .when().get("/comments?postId=1")
                .then()
                .extract()
                .response();

        // use hamcrest to check that the response body contains specific items.
        List<String> expectedEmails = Arrays.asList("Eliseo@gardner.biz", "Jayne_Kuhic@sydney.com", "Nikita@garfield.biz", "Lew@alysha.tv", "Hayden@althea.biz");
        assertThat(response.jsonPath().getList("email"), contains(expectedEmails.toArray(new String[0])));
    }

    @Test
    public void validateListContainsAllData() {
        //set the base uri
        RestAssured.baseURI = "https://reqres.in/api";
        Response response = given()
                .when().get("/users?page=2")
                .then()
                .extract()
                .response();

        // validating the first user in the list has correct values.
        response.then().body("data[0].id", is(7));
        response.then().body("data[0].email", is("michael.lawson@reqres.in"));
        response.then().body("data[0].first_name", is("Michael"));
        response.then().body("data[0].last_name", is("Lawson"));
        response.then().body("data[0].avatar", is("https://reqres.in/img/faces/7-image.jpg"));


    }

    @Test
    public void validateStatusCodeGetUser() {
        //set the base uri
        System.out.println("*********");
        Response response =
                given()
                        .header("x-api-key", "reqres-free-v1")
                        .queryParam("page", 2)
                        .queryParam("per_page", 3)
                        .queryParam("rtgsdr", 4)
                        .when()
                        .get("https://reqres.in/api/users");

        int actualStatusCode = response.statusCode(); // Rest assured
        assertThat(actualStatusCode, equalTo(200));


    }

    /*@Test
    public void validateResponseBodyGetPathParam(){
        String raceSeasonValue = "2017";
        Response response =
                given()
                        .header("x-api-key", "reqres-free-v1")
                        .pathParam("raceSeason", raceSeasonValue)
                        .when()
                        .get("http://ergast.com/api/f1/{raceSeason}/circuits.json");
        int actualStatusCode = response.statusCode();
        assertThat(actualStatusCode, equalTo(200));
        System.out.println(response.body().asString());
    }*/
    /*@Test
    public void validateStatusCodeForm(){
        RestAssured.baseURI = "https://reqres.in/api";

        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", "Sangeeta")
                .formParam("job", "QA Engineer")
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .response();
        //assert the response conatins the correct name and value
        response.then().body("name", equalTo("Sangeeta"));
        response.then().body("job", equalTo("QA Engineer"));

    }*/
    @Test(groups = "RegressionSuite")
    public void validateStatusCodeHeaderCode() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("Validatestatuscodeheadercode", "Validate status code header");
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
        System.out.println("validateStatusCodeHeaderCode executed susscessfully");

    }

    @Test (groups = "SmokeSuite")
    public void validateStatusCodeTwoHeader() {
        given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .header("Authorization", "bearer ssadsadasdasdasdasdasdsdd")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
        System.out.println("validateStatusCodeTwoHeader executed susscessfully");

    }

    @Test (groups = {"RegressionSuite", "SmokeSuite"})
    public void validateStatusCodeTwoHeaderWithMap() {

        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("Testvalidatestatuscodetwoheaderwithmap", "Validating the two header with map");
        Map<String, String> headers = new HashMap<>();
        headers.put("x-api-key", "reqres-free-v1");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "bearer ssadsadasdasdasdasdasdsad");

        given()
                .headers(headers)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
        System.out.println("validateStatusCodeTwoHeaderWithMap executed susscessfully");

    }

    @Test
    public void testFetchingHeaders() {
        Response response = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .extract()
                .response();
        Headers headers = response.headers();
        for (Header h : headers) {
            if (h.getName().equals("Server")) {
                assertEquals(h.getValue(), "cloudflar");
                System.out.println("Executed testFetchingHeaders");
            }


        }
    }


    @Test(groups = {"SmokeSuite", "RegressionSuite"})
    public void testDeleteMethod() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("testDeleteMethod", "Validating the delete method");
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .delete("https://reqres.in/api/users/2");
        int actualStatusCode = response.statusCode();
        assertEquals(actualStatusCode, StatusCode.NO_CONTENT.code);
        System.out.println("Executed testDeleteMethod");
    }



    @Test
    public void validatePropertyFileGetUser() throws IOException, ParseException {
        //set the base uri
        String serverAddress = PropertyReader.propertyReader("config.properties", "server");
        String endpoint = JsonReader.getTestData("endpoint");
        String URL = serverAddress + endpoint;
        System.out.println("Executing validatePropertyFileGetUser");
        Response response =
                given()
                        .header("x-api-key", "reqres-free-v1")
                        .queryParam("page", 2)
                        .queryParam("per_page", 3)
                        .queryParam("rtgsdr", 4)
                        .when()
                        .get(URL);

        int actualStatusCode = response.statusCode(); // Rest assured
        assertThat(actualStatusCode, equalTo(200));


    }

    @Test
    public void validateWithSoftAssertionUtil() {
        //set the base uri
        RestAssured.baseURI = "https://reqres.in/api";
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .when().get("/users?page=2")
                .then()
                .extract()
                .response();
        SoftAssertionUtil.assertEquals(response.getStatusCode(), StatusCode.NO_CONTENT.code, "Status Code is not 200");
        SoftAssertionUtil.assertAll();
        System.out.println("Validate withSoftAssertionUtil executed successfully");
    }

    @DataProvider(name = "TestData")
    public Object[][] getTestData() {
        return new Object[][]{

                {
                        "1", "John"
                },
                {
                        "2", "Michael"
                },
                {
                        "3", "Hanry"
                }
        };
    }
    @Test(dataProvider = "TestData" )
    @Parameters({"id", "name"})
    public void testEndPoint(String id, String name) {
        given()
        .header("x-api-key", "reqres-free-v1")
                .queryParam("id", id)
                .queryParam("name", name)
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200);
    }

    @Test
    public void Test() throws IOException,  ParseException {
        JsonReader.getJsonArrayData("languages", 0);
        JSONArray jsonArray = getJsonArray("Contacts");
        Iterator<String> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }



    }


}



