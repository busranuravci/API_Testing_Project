package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;


public class JsonPlaceHolderBaseUrl {


    protected RequestSpecification spec;

    @Before //this will run before each test method
    public void setUp(){
        spec = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri("https://jsonplaceholder.typicode.com").build();
    }

/*
URL (Uniform Resource Locator)
URI (Uniform Resource Identifier)

 the main difference between URL and URI is that
 a URL is a type of URI that identifies the location of a web resource,
 while a URI is a more general concept that can identify any type of resource.

 URL example: https://www.google.com/search?q=rest+api
 URI example: file:///Users/JohnDoe/Documents/myfile.txt
 URI example: urn:isbn:978-3-16-148410-0
 URI example: mailto:jane.doe@example.com
 jane.doe@example.com









 */







}
