package steps.step01getRequests;

import baseUrls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test04 extends HerOkuAppBaseUrl {

    @Test
    public void queryParamsTest(){
        /*
       Given
           https://restful-booker.herokuapp.com/booking
       When
           User sends get request to the URL
       Then
           Status code is 200
         And
             Among the data there should be someone whose firstname is "Jane" and lastname is "Doe"
    */
        //set the url ==>we add this part to the url
        spec.pathParam("first","booking").queryParams("firstname","Jane","lastname","Doe");
        //send the request and get the response
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();

        //assertion
        //1. way: greaterThan()
        response.
                then().
                body("",hasSize(greaterThan(0)));

        //2. way: ???
        assertTrue(response.asString().contains("bookingid"));//we get "bookingid" from response container
        //if there is any "bookingid", it means there is at least one Jane Doe

    }

    @Test
    public void softHardAssertion(){
         /*
      Given
          https://restful-booker.herokuapp.com/booking/6926
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
        //set the url
        spec.pathParams("first","booking","second",470);
        //set expected data

        //send request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //assertion
        //1. way:
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("John"),
                        "lastname",equalTo("Smith"),
                        "totalprice",equalTo(111),
                 //       "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2018-01-01"),
                        "bookingdates.checkout",equalTo("2019-01-01"),
                        "additionalneeds",equalTo("Breakfast"));

        //2. way: with JsonPath
        //HARD ASSERTION ==> restAssured library
        //we can extract the data from body to outside with JsonPath
        JsonPath jsonPath = response.jsonPath();
        assertEquals("John",jsonPath.getString("firstname"));
        assertEquals("Smith",jsonPath.getString("lastname"));
        assertEquals(111,jsonPath.getInt("totalprice"));
 //       assertEquals(true,jsonPath.getBoolean("depositpaid"));
        assertTrue(jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-01-01",jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01",jsonPath.getString("bookingdates.checkout"));
        assertEquals("Breakfast",jsonPath.getString("additionalneeds"));

        //SOFT ASSERTION ==> testNG library
        //there are three steps to follow

        //1. step:create softAssert object
        SoftAssert softAssert = new SoftAssert();
        //2. step: assertion
        assertEquals(200,response.statusCode());
        softAssert.assertEquals(jsonPath.getString("firstname"),"John","firstname did not matched");
        softAssert.assertEquals(jsonPath.getString("lastname"),"Smith","lastname did not matched");
        softAssert.assertEquals(jsonPath.getInt("totalprice"),111,"totalprice did not matched");
    //    softAssert.assertEquals(jsonPath.getString("depositpaid"),true,"depositpaid did not match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"),"2018-01-01","checkin did not matched");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"),"2019-01-01","checkout did not matched");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"),"Breakfast","additionalneeds did not matched");

        //3. step: assertAll() method collects all the assertion in softAssert container
        softAssert.assertAll();













    }












}
