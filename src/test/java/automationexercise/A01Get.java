package automationexercise;

import base_url.AutomationexerciseBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class A01Get extends AutomationexerciseBaseUrl {

    //API URL: https://automationexercise.com/api/productsList
    //Request Method: GET
    //Response Code: 200
    //Response JSON: All products list

    //Set the Url
    //Set the expected Data(Payload)
    //Send the Request and Get the Response
    //Do Assertion


    @Test
    public void a01() {

        //Set the Url
        spec.pathParam("first","productsList");

        //Set the expected Data(Payload)

        //Send the Request and Get the Response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion
        //1.Yol:
        response.then().assertThat().statusCode(200);

        //2.Yol:
        assertEquals(200,response.statusCode());



    }
}
