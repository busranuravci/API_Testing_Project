package steps.step01getRequests;

import baseUrls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Test01 extends HerOkuAppBaseUrl {

     /*
        Given
            https://restful-booker.herokuapp.com/booking/10
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */





    @Test
    public void responseTest(){

//        i) set the URL
        /*
         @Before//This will run before each test method
    public void setUp() {
        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
    }
         */
      spec.pathParams("first","booking","second",10);

//        ii) set the expected data

//        iii) set the request and get the response
        Response response = given().when().spec(spec).get("/{first}/{second}");
        response.prettyPrint();
//        iv) do assertion
        //status code: 200, content type: json, status line: HTTP/1.1 200 OK
        response.then().
                 statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");





    }

    @Test
    public void assertionTest(){

//        i) set the URL
        spec.pathParams("first","booking","second",10);
//        ii) set the expected data
        /*
{
    "firstname": "Eric",
    "lastname": "Jones",
    "totalprice": 538,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2017-10-16",
        "checkout": "2019-09-16"
    },
    "additionalneeds": "Breakfast"
}

 */
//        iii) set the request and get the response
        Response response = given().when().spec(spec).get("/{first}/{second}");
        response.prettyPrint();
//        iv) do assertion
        //we can query other assertions on our response
        assertTrue(response.header("Server").contains("Cowboy"));
        assertFalse(response.header("Via").contains("via"));//Via
       assertTrue(response.header("Connection").contains("keep-alive"));


        System.out.println("response.headers() = " + response.headers());
/*
response.headers() =>
Server=Cowboy
Connection=keep-alive
X-Powered-By=Express
Content-Type=application/json; charset=utf-8
Content-Length=172
Etag=W/"ac-/oebx3ssYWWAaL6uLbqgUoask/U"
Date=Sat, 25 Mar 2023 00:04:37 GMT
Via=1.1 vegur
 */
        System.out.println("response.time() = " + response.time());//response.time() = 1755

    }














}
