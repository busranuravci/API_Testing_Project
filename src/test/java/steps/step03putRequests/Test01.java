package steps.step03putRequests;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import testData.JsonPlaceHolderTestData;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
public class Test01 extends JsonPlaceHolderBaseUrl {

    @Test
    public void put01(){
      /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 7,
                 "title": "provident aut nobis culpa",
                 "completed": true
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									      "userId": 12,
                                          "id": 17,
                                          "title": "what's up bro",
                                          "completed": true
									   }
     */

        //Set the url
        spec.pathParams("first","todos","second",17);

        //set the expected data
        Map<String,Object> expectedData = new JsonPlaceHolderTestData().expectedDataMapMethod(
                12,
                "what's up bro",
                true
        );
        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response = given(spec).body(expectedData).put("{first}/{second}");
        response.prettyPrint();

        //assertions
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));


    }













}
