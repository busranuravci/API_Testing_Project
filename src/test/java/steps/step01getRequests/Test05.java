package steps.step01getRequests;

import baseUrls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Test05 extends PetStoreBaseUrl {

    @Test
    public void responseBody(){

         /*
    Given
        https://petstore.swagger.io/v2/pet/3467889
    When
         User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is “application/json”
    And
        Response body should be like;
         {
            "id": 6546754768,
            "category": {
                "id": 0,
                "name": "Cat"
            },
            "name": "Kitty",
            "photoUrls": [
                "string"
            ],
            "tags": [
                {
                    "id": 0,
                    "name": "string"
                }
            ],
            "status": "available"
        }

     */

        spec.pathParams("first","pet").queryParams("category.name","Cat","name","Kitty");

        Response response = given().spec(spec).when().get("{first}");
        response.prettyPrint();

        response.then().statusCode(200).
                contentType(ContentType.JSON).
                body("category.name",equalTo("Cat"),
                        "category.id",equalTo(0),
                        "name",equalTo("Kitty"),
                        "status",equalTo("available"));

    }


















}
