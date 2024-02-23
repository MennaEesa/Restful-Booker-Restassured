package testCases;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Model.UpdateBooking.getUpdateBookingBody;
import static io.restassured.RestAssured.given;
import static testCases.TC02_CreateBooking.Booking_id;

@Epic("Create NEw Booking")
@Story("Create Booking")
public class TC03_UpdateBooking extends testBase{

    String FirstName=faker.name().firstName();
    String LastName=faker.name().lastName();

    @Description("update new Booking with valid data")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1, description = "update new Booking with valid data")
    public void createUpdateBooking_P()  {
        Response response= given().log().all().auth().basic("admin","password123").header("Content-Type","application/json").header("Accept","application/json")
                .when().put("/booking/"+Booking_id)
                .then().log().all().assertThat()
                .statusCode(200)
                .extract().response();

    }
}
