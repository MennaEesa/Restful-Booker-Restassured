package testCases;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Model.UpdateBooking.getUpdateBookingBody;
import static io.restassured.RestAssured.given;
import static testCases.TC02_CreateBooking.Booking_id;

public class TC04_DeleteBooking {


    @Description("Delete an exist Booking id")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1, description = "Delete an exist Booking id")
    public void createTokenData_P() throws JsonProcessingException {
        Response response= given().log().all().auth().basic("admin","password123").header("Content-Type","application/json").header("Accept","application/json")
                .when().delete("/booking/"+Booking_id)
                .then().log().all().assertThat()
                .statusCode(200)
                .extract().response();
    }
}
