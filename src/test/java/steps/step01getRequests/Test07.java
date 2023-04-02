package steps.step01getRequests;
import baseUrls.ReqresInApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.anEmptyMap;
import static org.hamcrest.Matchers.hasSize;
public class Test07 extends ReqresInApiBaseUrl {

    @Test
    public void get01(){
         /*
        Given
            https://reqres.in/api/users/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */



        String url = "https://reqres.in/api/users/3";
        Response response = given().when().get(url);
        //response.prettyPrint();

        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");

    }


    @Test
    public void get02(){
        /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty

     */
        //Set the url
        spec.pathParams("first","users","second",23);

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        response.
                then().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found").
                header("Server","cloudflare").
                body("", anEmptyMap());//This is no key value pair in the response body. So it is an empty map.

    }





    @Test
    public void get03(){
         /*
       Given
           https://reqres.in/api/users/2
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response format should be “application/json”
       And
           “email” is “janet.weaver@reqres.in”,
       And
           “first_name” is "Janet"
       And
           “last_name” is "Weaver"
       And
           "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
    */

        spec.pathParams("first","users","second",2);
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data.email",equalTo("janet.weaver@reqres.in"),
                        "data.first_name",equalTo("Janet"),"data.last_name",equalTo("Weaver"),
                        "support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }







    @Test
    public void get04(){
/*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Brandon&lastname=Wilson
        When
            User sends get request to the URL
        Then
            Status code is 200
        And
            Among the data there should be someone whose firstname is "Brandon" and lastname is "Wilson"

 */
//        Given
//        https://restful-booker.herokuapp.com/booking?firstname=Brandon&lastname=Wilson

        spec.pathParam("first","booking").
                queryParams("firstname","Brandon","lastname","Wilson");

//        When
//        User sends get request to the URL
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

//        Then
//        Status code is 200
        Assert.assertEquals(200,response.getStatusCode());

//        And
//        Among the data there should be someone whose firstname is "Brandon" and lastname is "Wilson"
        Assert.assertTrue(response.asString().contains("bookingid"));

    }



    @Test
    public void get05(){
/*
         Given
           https://reqres.in/api/unknown/3
         When
             User send a GET request to the URL
         Then
             HTTP Status Code should be 200
         And
             Response content type is “application/json”
         And
             Response body should be like;(Soft Assertion)
         {
         "data": {
             "id": 3,
             "name": "true red",
             "year": 2002,
             "color": "#BF1932",
             "pantone_value": "19-1664"
         },
         "support": {
             "url": "https://reqres.in/#support-heading",
             "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
         }
 }
       */
        spec.pathParams("first","unknown","second",3);
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        JsonPath json = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(json.getString("data.name"),"true red","Name did not match");
        softAssert.assertEquals(json.getInt("data.year"),2002,"Year did not match");
        softAssert.assertEquals(json.getString("data.color"),"#BF1932","Color did not match");
        softAssert.assertEquals(json.getString("data.pantone_value"),"19-1664","pantone_value did not match");
        softAssert.assertEquals(json.getInt("data.id"),3,"Id did not match");

        softAssert.assertAll();

    }





    @Test
    public void get06(){
        /*
        Given
               https://reqres.in/api/unknown/
        When
             I send GET Request to the URL
        Then

             1)Status code is 200
             2)Print all pantone_values
             3)Print all ids greater than 3 on the console
               Assert that there are 3 ids greater than 3
             4)Print all names whose ids are less than 3 on the console
               Assert that the number of names whose ids are less than 3 is 2
     */
        spec.pathParam("first","unknown");
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data",hasSize(6));


//        1)Status code is 200
        response.then().assertThat().statusCode(200);

//        2)Print all pantone_values
        JsonPath json = response.jsonPath();
        List<String> pantone_values = json.getList("data.pantone_value");
        System.out.println("pantone_values = " + pantone_values);


//        3)Print all ids greater than 3 on the console
        List<Integer> ids = json.getList("data.findAll{it.id>3}.id");//Groovy
        System.out.println("ids = " + ids);//[4, 5, 6]

//        Assert that there are 3 ids greater than 3
        Assert.assertEquals(3, ids.size());

//        4)Print all names whose ids are less than 3 on the console
        List<String> names = json.getList("data.findAll{it.id<3}.name");
        System.out.println("names whose ids are less than 3 = " + names);

//        Assert that the number of names whose ids are less than 3 is 2
        Assert.assertEquals(2,names.size());

    }







}
