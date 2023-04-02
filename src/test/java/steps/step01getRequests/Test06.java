package steps.step01getRequests;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test06 extends JsonPlaceHolderBaseUrl {


     @Test
    public void test01(){
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

         spec.pathParam("first","todos");

         Response response = given().spec(spec).when().get("{first}");
         response.prettyPrint();

         //Status code is 200
         assertEquals(200,response.statusCode());

         //Print all ids greater than 190 on the console
         JsonPath jsonPath = response.jsonPath();
         List<Integer> idList = jsonPath.getList("id");
         System.out.println("idList = " + idList);

         // Assert that there are 10 ids greater than 190
         //1. Way: By using foreach loop
         int idsGreaterThan190 = 0;
         for (int w: idList){
             if (w>190){
                 idsGreaterThan190++;
             }
         }
         System.out.println("idsGreaterThan190 = " + idsGreaterThan190);
         assertEquals(10,idsGreaterThan190);//idsGreaterThan190 = 10

         //2nd Way: Recommended
         List<Integer> intListGroovy = jsonPath.getList("findAll{it.id>190}.id");
         //Groovy: Java based programming language
         System.out.println("intListGroovy = " + intListGroovy);
         //intListGroovy = [191, 192, 193, 194, 195, 196, 197, 198, 199, 200]
         assertEquals(10,intListGroovy.size());

         //Print all userIds whose ids are less than 5 on the console
         List<Integer> userIdList = jsonPath.getList("findAll{it.id<5}.userId");
         System.out.println("userIdList = " + userIdList);//userIdList = [1, 1, 1, 1]
         //Assert that the number of userIds whose ids are less than 5 is 4
         assertEquals(4,userIdList.size());

         //Print all titles whose ids are less than 5
         List<String> titlesList = jsonPath.getList("findAll{it.id<5}.title");
         System.out.println("titlesList = " + titlesList);
         //titlesList = [delectus aut autem, quis ut nam facilis et officia qui, fugiat veniam minus, et porro tempora]

         //Assert that "delectus aut autem" is one of the titles whose id is less than 5
         assertTrue(titlesList.contains("delectus aut autem"));

     }


}
