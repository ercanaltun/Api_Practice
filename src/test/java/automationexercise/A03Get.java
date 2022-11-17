package automationexercise;

import base_url.AutomationexerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class A03Get extends AutomationexerciseBaseUrl {

    //API URL: https://automationexercise.com/api/brandsList
    //Request Method: GET
    //Response Code: 200
    //Response JSON: All brands list


    @Test
    public void a03() {

       spec.pathParam("first","brandsList");

        Response response=given().spec(spec).when().get("/{first}");

        response.then().assertThat().statusCode(200);

        response.prettyPrint();



    }




}
