package automationexercise;

import base_url.AutomationexerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;

public class A02Post extends AutomationexerciseBaseUrl {

    //API URL: https://automationexercise.com/api/productsList
    //Request Method: POST
    //Response Code: 405
    //Response Message: This request method is not supported.


    @Test
    public void a02() {

        //Set the Url
        spec.pathParam("first","productsList");

        //Set the expected Data(Payload)

        //Send the Request and Get the Response
        Response response=given().spec(spec).when().post("/{first}");
       response.prettyPrint();



    }
}
