package steps.step01getRequests;

import baseUrls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Test02 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/0
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "JavaRestAssured"
        And
            Server is "Cowboy"
*/

    @Test
    public void negativeTest(){

      //  i) set the URL ==> Given
        spec.pathParams("first","booking","second",0);
        //ii) set the expected data

        // iii) set the request and get the response ==> when
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        //  iv) do assertion  ==> Then
        response.then().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found");

        //Response body contains "Not Found"
        //how to assert if response body contains any data!!
        //1.way
        assertTrue(response.asString().contains("Not Found"));
        //2.way
        assertEquals("Not Found",response.asString());
        //negative test
        assertFalse(response.asString().contains("JavaJUnit"));
        //positive test
        //1.way
        assertTrue(response.header("Server").contains("Cowboy"));
        //2.way
        assertEquals("Cowboy",response.header("Server"));

    }

}
