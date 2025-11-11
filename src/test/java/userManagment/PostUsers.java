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
    public void validatePostWithPojoListobject() throws IOException {

        List<String> listlanguages = new ArrayList<>();
        listlanguages.add("Java");
        listlanguages.add("Python");

        cityRequest cityRequest1 = new cityRequest();
        cityRequest1.setName("Bangalore");
        cityRequest1.setTemperature("30");
        cityRequest cityRequest2 = new cityRequest();
        cityRequest2.setName("Delhi");
        cityRequest2.setTemperature("40");
        List<cityRequest> cityRequests = new ArrayList<>();
        cityRequests.add(cityRequest1);
        cityRequests.add(cityRequest2);

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
    public void validatePatchtWithPojo() throws IOException {


        postRequestBody patchtRequestBody = new postRequestBody();
        patchtRequestBody.setJob("CEO");
        //patchtRequestBody.setName("Aditi Mudda");
        //postRequestBody.setLanguages(listlanguages);
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .body(patchtRequestBody)
                .patch("https://reqres.in/api/users/2");
        //postRequestBody postRequestBody = response.as(postRequestBody.class);
        //System.out.println(postRequestBody.getJob());


        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.getBody().asString());
        System.out.println("validatePatchtWithPojo executed successfully");
    }

    @Test
    public void validatePatchtWithResponsePojo() throws IOException {

String job = "CEO";

        postRequestBody patchtRequestBody = new postRequestBody();
        patchtRequestBody.setJob(job);
        //patchtRequestBody.setName("Aditi Mudda");
        //postRequestBody.setLanguages(listlanguages);
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .body(patchtRequestBody)
                .patch("https://reqres.in/api/users/2");
        postRequestBody postRequestBody = response.as(postRequestBody.class);
        System.out.println(postRequestBody.getLanguages());
        System.out.println(postRequestBody.getCityRequests());
        System.out.println(postRequestBody.getJob());
        assertEquals(postRequestBody.getJob(), job);
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.getBody().asString());
        System.out.println("validatePatchtWithPojo executed successfully");
    }
    @Test
    public void validatePostWithPojoListobjectResponse() throws IOException {

        String name = "Bangalore";
        String temperature = "30";

        List<String> listlanguages = new ArrayList<>();
        listlanguages.add("Java");
        listlanguages.add("Python");

        cityRequest cityRequest1 = new cityRequest();
        cityRequest1.setName(name);
        cityRequest1.setTemperature(temperature);
        //cityRequest cityRequest2 = new cityRequest();
        //cityRequest2.setName("Delhi");
        //cityRequest2.setTemperature("40");
        List<cityRequest> cityRequests = new ArrayList<>();
        cityRequests.add(cityRequest1);
        //cityRequests.add(cityRequest2);

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
        pojo.postRequestBody listobj = response.as(pojo.postRequestBody.class);
        System.out.println(listobj.getCityRequests().get(0).getName());
        System.out.println(listobj.getCityRequests().get(0).getTemperature());
        System.out.println(listobj.getLanguages());
        assertEquals(listobj.getCityRequests().get(0).getName(), name);
        assertEquals(listobj.getCityRequests().get(0).getTemperature(), temperature);

        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println(response.getBody().asString());
        System.out.println("validatePostWithString executed successfully");


    }
}
