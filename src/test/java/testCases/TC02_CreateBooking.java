package testCases;

import Pojo.Create_Token_Pojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import util.Utilities;

import java.io.IOException;

import static Model.CreateBooking.getCreateBookingBody;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static util.Utilities.getRandomFirstName;

@Epic("Create NEw Booking")
@Story("Create Booking")
public class TC02_CreateBooking extends testBase {

    //    String FirstName=faker.name().firstName();
    String FirstName = Utilities.getSingleJsonData(System.getProperty("user.dir") + "/src/test/resources/testdata.json", "firstname");
    //    String LastName=getRandomFirstName(true);
    String LastName = Utilities.getExcelData(0, 0, "Sheet1");

    public TC02_CreateBooking() throws IOException, ParseException {
    }

    public Response response;
    public static String Booking_id;

    @Description("Create new Booking with valid data")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1, description = "Create new Booking with valid data")
    public void createBooking_P() throws JsonProcessingException {
        response = given().log().all().header("Content-Type", "application/json")
                .body(getCreateBookingBody(FirstName, LastName))
                .when().post("/booking")
                .then().log().all().assertThat()
                .statusCode(200)
                .extract().response();
        Booking_id = response.jsonPath().getString("bookingid");
        System.out.println(Booking_id);

    }
}
