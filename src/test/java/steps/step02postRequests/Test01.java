package steps.step02postRequests;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import testData.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Test01 extends JsonPlaceHolderBaseUrl {

    @Test
    public void post01(){
        /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
              }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

        /*
    De-Serialization: Json datanın Java objesine çevrilmesi.
    Serialization: Java objesinin, Json dataya çevrilmesi.
    2 türlü De-Serialization yapacağız:
        i) Gson: Google tarafından üretilmiştir.
        ii) Object Mapper: En popüleri
    */


        //Set the url
     spec.pathParam("first","todos");

        //Set the expected data
        Map<String,Object> expectedData = new HashMap<>();//payload
        expectedData.put("userId",55);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);
        expectedData.put("id",201);
        System.out.println("expectedData = " + expectedData);
        //expectedData = {completed=false, id=201, title=Tidy your room, userId=55}


        //Send the request and get the response
     Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("{first}");
     response.prettyPrint();

       //do assertion
        //De-Serialization--> Json to Java
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("id"),actualData.get("id"));

    }

    @Test
    public void test02(){

        //set the url
        spec.pathParam("first","todos");

        //set the expected data

        //Create an object from JsonPlaceHolderTestData class and
        //use the expectedDataMapMethod to create "expectedData".
        //Recommended way
        Map<String,Object> expectedData = new JsonPlaceHolderTestData().
                        expectedDataMapMethod(
                                55,
                                "Tidy your room",
                                false);
        System.out.println("expectedData = " + expectedData);
        //expectedData = {completed=false, title=Tidy your room, userId=55}

        //send the request and get the response
        Response response = given().spec(spec).
                contentType(ContentType.JSON).
                body(expectedData).when().post("{first}");
        response.prettyPrint();

        //do assertion
        Map<String,Object> actualData = response.as(HashMap.class);//de-serialization
        System.out.println("actualData = " + actualData);
        //expectedData = {completed=false, title=Tidy your room, userId=55}

        assertEquals(201,response.getStatusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));


    }









}
