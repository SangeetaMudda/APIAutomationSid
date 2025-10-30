package userManagment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import core.StatusCode;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import pojo.cityRequest;
import pojo.postRequestBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.testng.AssertJUnit.assertEquals;

public class PostUsers {

    private static FileInputStream fileInputStreamMethod(String requestBodyFileName) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(
                    new File(System.getProperty("user.dir") + "/resources/TestData/" + requestBodyFileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        }
        return fileInputStream;
    }

    @Test
    public void validatePostWithString(){
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .body("{\"name\":\"SangeetaMudda\",\"job\":\"Director\"}")
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println(response.getBody().asString());
        System.out.println("validatePostWithString executed successfully");


    }
    @Test
    public void validatePutWithString(){
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .body("{\"name\":\"AditiMudda\",\"job\":\"CEO\"}")
                .when()
                .put("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.getBody().asString());
        System.out.println("validatePostWithString executed successfully");


    }
    @Test
    public void validatePostWithJsonFile() throws IOException {
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .body(IOUtils.toString(fileInputStreamMethod("PostRequestBody.json")))
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println(response.getBody().asString());
        System.out.println("validatePostWithString executed successfully");


    }
    @Test
    public void validatePutWithJsonFile() throws IOException {
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .body(IOUtils.toString(fileInputStreamMethod("PutRequestBody.json")))
                .when()
                .put("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.getBody().asString());
        System.out.println("validatePutWithJsonFile executed successfully");


    }
    @Test
    public void validatePostWithPojo() throws IOException {

        List<String> listlanguages = new ArrayList<>();
        listlanguages.add("Java");
        listlanguages.add("Python");
        postRequestBody postRequestBody = new postRequestBody();
        postRequestBody.setJob("Director");
        postRequestBody.setName("SangeetaMudda");
        postRequestBody.setLanguages(listlanguages);
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .body(postRequestBody)
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println(response.getBody().asString());
        System.out.println("validatePostWithString executed successfully");


    }
    @Test
    public void validatePutWithPojo() throws IOException {


        postRequestBody putRequestBody = new postRequestBody();
        putRequestBody.setJob("CEO");
        putRequestBody.setName("Aditi Mudda");
        //postRequestBody.setLanguages(listlanguages);
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .body(putRequestBody)
                .put("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.getBody().asString());
        System.out.println("validatePutWithPojo executed successfully");


    }

    @Test
    public void validatePostWithPojowithcity() throws IOException {

        List<String> listlanguages = new ArrayList<>();
        listlanguages.add("Java");
        listlanguages.add("Python");
        cityRequest cityreq1 = new cityRequest();
        cityreq1.setName("Bangalore");
        cityreq1.setTemperature("30");
        cityRequest cityreq2 = new cityRequest();
        cityreq2.setName("Delhi");
        cityreq2.setTemperature("40");
        List<cityRequest> cityRequests = new ArrayList<>();
        cityRequests.add(cityreq1);
        cityRequests.add(cityreq2);
        postRequestBody postRequestBody = new postRequestBody();
        postRequestBody.setJob("Director");
        postRequestBody.setName("SangeetaMudda");
        postRequestBody.setLanguages(listlanguages);
        postRequestBody.setCityRequests(cityRequests);
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .body(postRequestBody)
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println(response.getBody().asString());
        System.out.println("validatePostWithPojowithcity executed successfully");


    }





}
