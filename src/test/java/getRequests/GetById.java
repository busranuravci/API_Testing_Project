package getRequests;

import baseUrls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class GetById extends HerOkuAppBaseUrl {
    /*
       Given
           https://restful-booker.herokuapp.com/booking/1
       When
           User send Get request
       Then
           Status code should be 200
       And
           Body should be like:
           {
               "firstname": "Eric",
               "lastname": "Brown",
               "totalprice": 861,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2020-02-18",
                   "checkout": "2022-04-23"
               },
               "additionalneeds": "Breakfast"
           }
        */
    @Test
    public void GetById() {
        //Set the url
        spec.pathParams("first", "booking", "second", 11);

        //Set the expected data

        //Send the request and the  response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        response.
                then().
                statusCode(200).
                body("firstname", equalTo("Jane"),
                        "lastname", equalTo("Doe"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds", equalTo("Extra pillows please"));

    }






}
