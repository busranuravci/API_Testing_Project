package getRequests;


import baseUrls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get06 extends HerOkuAppBaseUrl {
    /*
      Given
          https://restful-booker.herokuapp.com/booking/3151
      When
          User send a GET request to the URL
      Then
          HTTP Status Code should be 200
      And
          Response content type is "application/json"
      And
          Response body should be like;
              {
                  "firstname": "John",
                  "lastname": "Smith",
                  "totalprice": 111,
                  "depositpaid": true,
                  "bookingdates": {
                      "checkin": "2018-01-01",
                      "checkout": "2019-01-01"
                  },
                  "additionalneeds": "Breakfast"
              }
   */
    @Test
    public void get06(){
        //Set the URL
        spec.pathParams("first","booking","second",3151);

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        //1st Way:
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("John"),
                        "lastname",equalTo("Smith"),
                        "totalprice",equalTo(111),
                        "bookingdates.checkin",equalTo("2018-01-01"),
                        "bookingdates.checkout",equalTo("2019-01-01"),
                        "additionalneeds",equalTo("Breakfast")

                );


        //2. way: we will use JsonPath class

        JsonPath jsonPath = response.jsonPath();//we can extract the data from body to outside with JsonPath

        //Hard assertion
        assertEquals("John",jsonPath.getString("firstname"));
        assertEquals("Smith",jsonPath.getString("lastname"));
        assertEquals(111,jsonPath.getInt("totalprice"));
        assertTrue(jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-01-01",jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01",jsonPath.getString("bookingdates.checkout"));
        assertEquals("Breakfast",jsonPath.getString("additionalneeds"));

        //3. way:  TestNG softAssert
        //to do soft assertion follow these three steps

        //1. step:create softAssert object with

        SoftAssert softAssert = new SoftAssert();

        //2nd Step: Do Assertion
        assertEquals(200, response.statusCode());
        softAssert.assertEquals(jsonPath.getString("firstname"), "John", "firstname did NOT match");
        softAssert.assertEquals(jsonPath.getString("lastname"), "Smith", "lastname did NOT match");
        softAssert.assertEquals(jsonPath.getInt("totalprice"), 111, "totalprice did NOT match");
        softAssert.assertEquals(jsonPath.getBoolean("depositpaid"), true, "depositpaid did NOT match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2018-01-01", "checkin did NOT match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"), "2019-01-01", "checkout did NOT match");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"), "Breakfast", "additionalneeds did NOT match");

        //3rd Step: Use assertAll() method
        softAssert.assertAll();














    }


}
