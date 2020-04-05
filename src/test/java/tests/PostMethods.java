package tests;

import config.ReadPropertyFile;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostMethods {

    public ReadPropertyFile prop;
    File payload = new File(System.getProperty("user.dir") + "/src/test/resources/payload.json");

    public PostMethods() {
        prop = new ReadPropertyFile();
    }

    @Test
    public void testPostMethodUsingPayloadJson() throws IOException {
        Response response = given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post(prop.getKey("HOST") + "/booking");
        response
                .then()
                .statusCode(200)
                .log();
        String actual = response.then().contentType(ContentType.JSON).extract().path("booking.firstname");
        String expected = "James";
        Assert.assertEquals(actual, (expected));
    }

    @Test
    public void testPostMethodUsingParams() throws IOException {
        Response response = given()
                .contentType("application/json")
                .queryParam("firstname", "George")
                .queryParam("lastname", "brown")
                .queryParam("totalprice", 2000)
                .queryParam("depositpaid", false)
                .queryParam("additionalneeds", "late checking")
                .when()
                .post(prop.getKey("apiurl") + "/enquiries");

        String actual = response.then().contentType(ContentType.JSON).extract().path("booking.firstname");
        String expected = "James";
        Assert.assertEquals(actual, (expected));
    }

    @Test
    public void testPostMethodUsinghaspmap() throws IOException {
        Map<String, String> body = new HashMap<>();
        body.put("CountryCode", "MY");
        body.put("Page", "1");
        body.put("keyword", "mah sing group berhad");

        Response response = given()
                .contentType("application/json")
                .body(body)
                .when()
                .post(prop.getKey("apiurl") + "/enquiries");

        String actual = response.then().contentType(ContentType.JSON).extract().path("booking.CountryCode");
        String expected = "MY";
        Assert.assertEquals(actual, (expected));
    }

}
