package getRequests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestAndResponse {

    /*
    1) Postman is used for manuel API testing
    2) we use RestAssured library for API automation testing
    3) to type automation script follow these steps
       a) understand the requirement
       b) type test cases
          to type the test cases we use "Gherkin Language"
          the keywords are:
             x) given: it is used for pre-conditions
             y) When: it is used for actions(requests..)
             z) Then: it is used for outputs(assertion..)
             t) And: it is used for multiple usage of Given,When and Then
       c) starts to type "automation script "
          i) set the URL
          ii) set the expected data
          iii) set the request and get the response
          iv) do assertion

     */

    public static void main(String[] args) {

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

        String url = "https://restful-booker.herokuapp.com/booking/10";
        Response response = given().when().get(url);//User sends a GET Request to the url
               response.prettyPrint();
               //print() method returns the data in a single line
               //we can use without when() method also

        //HTTP Status Code should be 200
        System.out.println("response.statusCode() = " + response.statusCode());//200

        //Content Type should be JSON
        System.out.println("response.contentType() = " + response.contentType());
        //application/json; charset=utf-8

        //Status Line should be HTTP/1.1 200 OK
        System.out.println("response.statusLine() = " + response.statusLine());
        //response.statusLine() = HTTP/1.1 200 OK

        //how to see the "Header" on console
        System.out.println("response.header(\"Server\") = " + response.header("Server"));
        //response.header("Server") = Cowboy
        System.out.println("response.header(\"Via\") = " + response.header("Via"));
        //response.header("Via") = 1.1 vegur
        System.out.println("response.header(\"Connection\") = " + response.header("Connection"));
        //response.header("Connection") = keep-alive

        //how to see "headers" on console
        System.out.println("response.headers() = " + response.headers());
        /*
response.headers() = Server=Cowboy
Connection=keep-alive
X-Powered-By=Express
Content-Type=application/json; charset=utf-8
Content-Length=139
Etag=W/"8b-xHcnGtdQPMF25APxK7uIhfn1lh4"
Date=Sun, 19 Mar 2023 15:57:32 GMT
Via=1.1 vegur
         */

        //how to see "Time" on console
        System.out.println("response.time() = " + response.time());
        //response.time() = 1595


    }











}
