package getRequests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get02 {


/*
                  NEGATIVE (404)

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
    public void get02(){

//        i) set the URL
        String url = "https://restful-booker.herokuapp.com/booking/0";

//        ii) set the expected data


//        iii) set the request and get the response
        Response response = given().get(url);
        response.prettyPrint();



//       iv) do assertion
        response.
                then().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found");


        //how to assert if response body contains any data
        //Response body contains "Not Found"
        assertEquals("Not Found",response.asString());
        // assertTrue(response.asString().contains("Not Found"));


        //Response body does not contain "JavaRestAssured"
        assertFalse(response.asString().contains("JavaRestAssured"));//passed
        //assertFalse() method passes if the value between parenthesis is "false"


        //Server is "Cowboy"
        assertTrue(response.header("Server").contains("Cowboy"));
        //we can also use assertEquals
        //assertTrue() method passes if the value between parenthesis is "true"
        assertEquals("Cowboy",response.header("Server"));//2nd way==> recommended
        // also:  String server = response.header("Server");
        //        assertEquals("Cowboy", server);




    }








}
