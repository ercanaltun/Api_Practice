package automationexercise;

import base_url.AutomationexerciseBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class A04Put extends AutomationexerciseBaseUrl {

    //API URL: https://automationexercise.com/api/brandsList
    //Request Method: PUT
    //Response Code: 405
    //Response Message: This request method is not supported.


    @Test
    public void a04() {

        spec.pathParam("1","brandsList");

        Response response=given().spec(spec).when().put("/{1}");
        response.prettyPrint();

    }
}
