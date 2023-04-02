package postRequests;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import testData.JsonPlaceHolderTestData;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
public class Post01 extends JsonPlaceHolderBaseUrl {
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
            Status code is 201    //in post requests, status code is 201
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

    @Test
    public void post01() {
        //Set the url
        spec.pathParam("first", "todos");

        //Set the expected data <==> Payload, means the data you will send
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 55);
        expectedData.put("title", "Tidy your room");  // This is not recommended way
        expectedData.put("completed", false);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response  //we send our expected data as body in the request
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("{first}");
        response.prettyPrint();

        //serialization ==>
        //body(expectedData) --> for this part, we need some libraries to convert java language to Json language

        //Do Assertion
        //De-Serialization--> Json to Java !!!!
        //for the lists, we used jsonPath.getList("id"),we can also use response.getString()
        //for maps we use as(HashMap.class) method
        //and for body we use response.body(path, Hamcrest Matchers(hasItem(),..))
        //response.as(Pojo.class)
        //ObjectMapper().readValue(response.asString(){it is a string data},Hashmap.class)
        //ObjectMapper().readValue(response.asString(){it is a string data},Pojo.class)
        //all of them are de-serialization

        Map<String, Object> actualData = response.as(HashMap.class);//as() method convert the Json to Java
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
    //   assertEquals(expectedData,actualData);//for this assertion, both maps has to have elements in the same order


    }


    @Test
    public void post01b() {
        //Set the url
        spec.pathParam("first", "todos");

        //Set the expected data
        //Create an object from JsonPlaceHolderTestData class and use the expectedDataMapMethod to create "expectedData".
        Map<String, Object> expectedData = new JsonPlaceHolderTestData().expectedDataMapMethod(55, "Tidy your room", false);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("{first}");
        response.prettyPrint();

        //Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);//De-Serialization
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

    }











}
