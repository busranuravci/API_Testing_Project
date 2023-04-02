package getRequests;

import baseUrls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Get05 extends HerOkuAppBaseUrl {

/*
       Given
           https://restful-booker.herokuapp.com/booking
       When
           User sends get request to the URL
       Then
           Status code is 200
         And
             Among the data there should be someone whose firstname is "Jane" and lastname is "Doe"
    */


    @Test
    public void get05(){

        //set the url
        //https://restful-booker.herokuapp.com/booking/?firstname=Jane&lastname=Doe
        spec.pathParam("first","booking").
        queryParams("firstname","Jane","lastname","Doe");


        //set the expected data


        //send the request and get the response
        //we can use spec in the given
        Response response = given(spec).when().get("{first}");
           response.prettyPrint();

        //do assertion
        //1. way:
        response.
                then().
                statusCode(200).
                body("",hasSize(greaterThan(0)));

        //2. way:
        assertTrue(response.asString().contains("bookingid"));
        assertEquals(200,response.statusCode());








    }













}
