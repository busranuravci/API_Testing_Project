package getRequests;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get08 extends JsonPlaceHolderBaseUrl {
 /*
        Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200
			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 3)Print all userIds whose ids are less than 5 on the console
			   Assert that the number of userIds whose ids are less than 5 is 4
			 4)Print all titles whose ids are less than 5
			   Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */

    @Test
    public void get08() {
        //Set the url
        spec.pathParam("first", "todos");

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).when().get("{first}");
        response.prettyPrint();

        //Do assertion
//        1)Status code is 200
        assertEquals(200, response.statusCode());

//        2)Print all ids greater than 190 on the console
        JsonPath jsonPath = response.jsonPath();
        List<Integer> idList = jsonPath.getList("id");
        System.out.println("idList = " + idList);

//        Assert that there are 10 ids greater than 190
        //1. Way: By using foreach loop
        int idsGreaterThan190 = 0;
        for (int w : idList) {
            if (w > 190) {
                idsGreaterThan190++;
            }
        }
        System.out.println("idsGreaterThan190 = " + idsGreaterThan190);//idsGreaterThan190 = 10
        assertEquals(10, idsGreaterThan190);

        //2nd Way: Recommended  ==> Groovy language: Java based programming language
        List<Integer> intListGroovy = jsonPath.getList("findAll{it.id>190}.id");//it --> item
        System.out.println("intListGroovy = " + intListGroovy);//".id" part gives just "id"s
        // intListGroovy = [191, 192, 193, 194, 195, 196, 197, 198, 199, 200]
        assertEquals(10, intListGroovy.size());

        //2.a: way
        System.out.println("2.a way: " + jsonPath.getList("findAll{it.id>190}").size());//2.a way: 10
        System.out.println("findAll{it.id==200}  => " + jsonPath.getList("findAll{it.id==200}"));
        //findAll{it.id==200}  => [{userId=10, id=200, title=ipsam aperiam voluptates qui, completed=false}]
        System.out.println("findAll{it.id >198}  => " + jsonPath.getList("findAll{it.id >198}"));
        //findAll{it.id >198}  => [{userId=10, id=199, title=numquam repellendus a magnam, completed=true},
        //                     => {userId=10, id=200, title=ipsam aperiam voluptates qui, completed=false}]



//        3)Print all userIds whose ids are less than 5 on the console
        List<Integer> userIdList = jsonPath.getList("findAll{it.id<5}.userId");//{if part}/.userId{printing part}
        System.out.println("userIdList = " + userIdList);//userIdList = [1, 1, 1, 1]

//        Assert that the number of userIds whose ids are less than 5 is 4
        assertEquals(4, userIdList.size());

//        4)Print all titles whose ids are less than 5
        List<String> titleList = jsonPath.getList("findAll{it.id<5}.title");
        System.out.println("titleList = " + titleList);

//        Assert that "delectus aut autem" is one of the titles whose id is less than 5
        assertTrue(titleList.contains("delectus aut autem"));




    }














}
