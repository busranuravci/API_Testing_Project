package steps.step01getRequests;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;


public class Test03 extends JsonPlaceHolderBaseUrl {

    //extends-inheritance
    //@before annotation to handle nullPointerException
    //baseUrl package-class


    @Test
    public void hardSoftAssertionTest(){
        /*
      Given
          https://jsonplaceholder.typicode.com/todos/23
      When
          User send GET Request to the URL
      Then
          HTTP Status Code should be 200
And
    Response format should be “application/json”
And
    “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
And
    “completed” is false
And
    “userId” is 2
   */

        //1. Step: set the url
        //we specify our request with RequestSpecification
        spec.pathParams("first","todos","second",23);

        //2. Step: set the expected data

        //3. step: Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4. step: do assertion

        //hard assertion ==> multiple body() method
        response.then().statusCode(200).contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed",equalTo(false)).
                body("userId",equalTo(2));
        //soft assertion ==> single body() method
        response.then().statusCode(200).contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed",equalTo(false),
                        "userId",equalTo(2));

    }

    @Test
    public void hasSizeHasItemTest(){

            /*
   Given
       https://jsonplaceholder.typicode.com/todos
   When
    I send a GET request to the Url
And
    Accept type is "application/json"
Then
    HTTP Status Code should be 200
And
    Response format should be "application/json"
And
    There should be 200 todos
And
    "quis eius est sint explicabo" should be one of the todos title
And
    2, 7, and 9 should be among the userIds
*/

        //set the url ==> there is just one parameter in the task,
        // so we use pathParam() method
        spec.pathParam("first","todos");

        //send the request and get the response
        Response response = given().spec(spec).accept(ContentType.JSON).when().get("/{first}");
        //we can assign contentType in response object creation
        //get() must be the last
        response.prettyPrint();

        //do assertion
        response.then().statusCode(200).
                body("",hasSize(200), //to get size of all the response Data, we use "empty"
            //but body("title",hasSize(200));  ==> also gives the size of all the data
                        "title",hasItem("quis eius est sint explicabo"),
                        "userId",hasItems(2,7,9));
        //in negative scenario
        //body("id",hasSize(201)); ==> JSON path id doesn't match.
        //                         ==> Expected: a collection with size <201>

    }












}
