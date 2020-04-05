package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import config.ReadPropertyFile;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.StringContains.containsString;

import java.io.IOException;

public class GetMethods {

    public ReadPropertyFile prop;

    public GetMethods() {
        prop = new ReadPropertyFile();
    }

    @Test
    public void testGetAllBookingIDStatus() throws IOException {
        given()
                .contentType("application/json")
                .when().get(prop.getKey("HOST") + "/booking")
                .then()
                .statusCode(200)
				.body(containsString("bookingid"))
                .log();
    }

    @Test
    public void testGetInvalidBookingEndpoint() throws IOException {
        given()
                .contentType("application/json")
                .when().get(prop.getKey("HOST") + "/bookings")
                .then()
                .statusCode(404);
    }

    @Test
    public void testGetBookingByID() throws IOException {
        given()
                .contentType("application/json")
                .when().get(prop.getKey("HOST") + "/booking/5")
                .then()
				.statusCode(200)
                .assertThat()
				.body("firstname", equalTo("Susan"));

    }

    @Test
    public void testLatency() throws Throwable {
		Response response = given()
				.contentType("application/json")
				.when()
				.get(prop.getKey("HOST") + "/booking");
        long latency = response.getTime();
        if (latency > 4000) {
            throw new Exception(
                    "Response Time Failed: Should be Less than 4000ms;" + "Actual Response Time: " + latency);
        }
    }

}
