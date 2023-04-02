package getRequests;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class Get04 extends JsonPlaceHolderBaseUrl {

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

    @Test
    public void get04(){

        //set the url
        spec.pathParam("first","todos");

        //set the expected data

        //send the request and get the response
        Response response = given().spec(spec).accept(ContentType.JSON).
                        when().get("/{first}"); //get() must be the last
        response.prettyPrint();

        //do assertion

        response.then().
                 statusCode(200).
                contentType(ContentType.JSON).
                //body("id",hasSize(201));//JSON path id doesn't match.
                 //Expected: a collection with size <201>
     //   body("id",hasSize(200));
    //    body("title",hasSize(200));
    //    body("UserId",hasSize(200));
        body("",hasSize(200),//there should be 200 todos
            "title",hasItem("quis eius est sint explicabo"), //"quis eius est sint explicabo" should be one of the todos title (contain() method)
            "userId",hasItems(2,7,9));//2, 7, and 9 should be among the userIds


    }


}
