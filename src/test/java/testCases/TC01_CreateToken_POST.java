package testCases;

import Pojo.Create_Token_Pojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static Model.Create_Token.getCreateTokenBody;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class TC01_CreateToken_POST extends testBase {

    // 1- Given [param , auth , header . body]
    // 2- When [Get - Post , .. + ENd point]
    // 3- Then [assertion results]
    @Test(priority = 1, description = "Create Token with valid username and password")
    public void createTokenData_P() throws JsonProcessingException {
        Create_Token_Pojo createTokenPojo = new Create_Token_Pojo("admin","password123");
        ObjectMapper mapper = new ObjectMapper();

        JSONObject bodyJson=new JSONObject();
        JSONArray arr=new JSONArray();

        bodyJson.put("username","admin");
        bodyJson.put("password","password123");
        arr.add(bodyJson);
        System.out.println(arr);

     Response response= given().log().all().header("Content-Type","application/json")
//                .body("{\n" +
//                        "    \"username\" : \"admin\",\n" +
//                        "    \"password\" : \"password123\"\n" +
//                        "}")
//             .body(getCreateTokenBody("admin","password123"))
//             .body(mapper.writeValueAsString(createTokenPojo))
               .body(bodyJson)
                .when().post("/auth")
                .then().log().all().assertThat()
                 .statusCode(200)
             .body("token",notNullValue())
             .extract().response();
         // Check Token is not empty
        Assert.assertFalse(response.jsonPath().getString("token").isEmpty());
        SoftAssert softAssert =new SoftAssert();
        softAssert.assertFalse(response.jsonPath().getString("token").isEmpty());
        softAssert.assertAll();
    }


    @Test(priority = 2, description = "Create Token with invalid username and password")
    public void createTokenInValidData_N(){
       given().log().all().header("Content-Type","application/json")
                .body("{\n" +
                        "    \"username\" : \"<Menna>\",\n" +
                        "    \"password\" : \"Menna123\"\n" +
                        "}")
                .when().post("/auth")
                .then().log().all().assertThat()
                .statusCode(200)
               .body("reason",equalTo("Bad credentials"));
    }

    @Test(priority = 3, description = "Create Token with invalid username and valid password")
    public void createTokenInValidUserName_N(){
        given().log().all().header("Content-Type","application/json")
                .body("{\n" +
                        "    \"username\" : \"<Menna>\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .when().post("/auth")
                .then().log().all().assertThat()
                .statusCode(200)
                .body("reason",equalTo("Bad credentials"));
    }


    @Test(priority = 4, description = "Create Token with valid username and invalid password")
    public void createTokenInValidPassword_N(){
        given().log().all().header("Content-Type","application/json")
                .body("{\n" +
                        "    \"username\" : \"<admin>\",\n" +
                        "    \"password\" : \"Menna123\"\n" +
                        "}")
                .when().post("/auth")
                .then().log().all().assertThat()
                .statusCode(200)
                .body("reason",equalTo("Bad credentials"));
    }

    @Test(priority = 5, description = "Create Token with invalid Request method")
    public void createTokenInValidRequestMethod_N(){
        given().log().all().header("Content-Type","application/json")
                .body("{\n" +
                        "    \"username\" : \"<admin>\",\n" +
                        "    \"password\" : \"Menna123\"\n" +
                        "}")
                .when().get("/auth")
                .then().log().all().assertThat()
                .statusCode(405);
    }

    @Test(priority = 6, description = "Create Token with invalid EndPoint")
    public void createTokenInValidEndPoint_N(){
        given().log().all().header("Content-Type","application/json")
                .body("{\n" +
                        "    \"username\" : \"<admin>\",\n" +
                        "    \"password\" : \"Menna123\"\n" +
                        "}")
                .when().post("/auth123")
                .then().log().all().assertThat()
                .statusCode(404);
    }

    @Test(priority = 7, description = "Create Token with invalid Auth")
    public void createTokenInValidAuth_N(){
        given().log().all().auth().basic("test","test").header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"username\" : \"<admin>\",\n" +
                        "    \"password\" : \"Menna123\"\n" +
                        "}")
                .when().post("/auth")
                .then().log().all().assertThat()
                .statusCode(404);

    }

    @Test(priority = 8, description = "Create Token with invalid header")
    public void createTokenInValidHeader_N(){
        given().log().all().header("Type","application/json")
                .body("{\n" +
                        "    \"username\" : \"<admin>\",\n" +
                        "    \"password\" : \"Menna123\"\n" +
                        "}")
                .when().post("/auth")
                .then().log().all().assertThat()
                .statusCode(400);
    }
}
