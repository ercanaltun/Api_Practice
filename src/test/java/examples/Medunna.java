package examples;

import base_url.MedunnaBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static utilities.AuthenticationGIM.generateToken;

public class Medunna extends MedunnaBaseUrl {

    @Test
    public void name() {

        spec.pathParams("1","physician","2","147333");

        Response response=given().spec(spec).contentType(ContentType.JSON).headers("Authorization","Bearer " + generateToken()).when().get("/{1}/{2}");

        response.prettyPrint();
    }
}
