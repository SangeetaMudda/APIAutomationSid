package userManagment;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class UploadDownload {
    @Test
    public void FileuploadExample(){

        File file = new File("resources/demo.txt");
        Response response = given()
                .multiPart("file", file)
                .when()
                .post("/upload");
        System.out.println(response.getStatusCode());


    }
}
