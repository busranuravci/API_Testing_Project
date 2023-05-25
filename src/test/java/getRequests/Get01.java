package getRequests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {

     /*
                POSITIVE (200)

   Given
       https://restful-booker.herokuapp.com/booking/55
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be JSON  >> application/json
   And
       Status Line should be HTTP/1.1 200 OK
*/

    @Test
    public void get01() {

//        1) set the URL
        String url = "https://restful-booker.herokuapp.com/booking/55";

//        2) set the expected data


//        3) set the request and get the response
        Response response = given().get(url);
        response.prettyPrint();

//        4) do assertion
         response.
                 then().
                 statusCode(200).
                 contentType("application/json").
                 statusLine("HTTP/1.1 200 OK");


    }

}
