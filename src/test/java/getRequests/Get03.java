package getRequests;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get03 extends JsonPlaceHolderBaseUrl {

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

    @Test
    public void get03(){
        //1. Step: set the url

   //     String url = "https://jsonplaceholder.typicode.com/todos/23";
        //this is not recommended way --> we will create baseUrls package
        spec.pathParams("first","todos","second",23);

        //2. Step: set the expected data


        //3. step: Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4. step: do assertion
        //1. way:hard assertion
        response.
                then().
                statusCode(200).
                contentType("application/json").
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("userId",equalTo(2)).
                body("completed",equalTo(false));


        //2. way:soft assertion
        //in soft assertion when one of them fail, soft assertion continues to run other assertion
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "userId",equalTo(2),
                        "completed",equalTo(false));

        /*
        Note 1: when you run the code, Java stops execution after first failure.
               So, assertion after the failure will not run be executed.
               But the assertions before failure will pass because there executed and there were no failure
        Note 2: if you type your code as execution will stop in the first failure.
                this is called "hard assertion"
        Note 3: if you type your code as execution will not stop in the first failures.
                this is called "soft assertion"

        Note 4: if you use multiple body method, it will work like "hard assertion"
        Note 5: if you do multiple assertion in a single body method.it will work like "soft assertion"
         */




    }

















}
