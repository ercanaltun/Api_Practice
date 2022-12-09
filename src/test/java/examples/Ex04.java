package examples;


import base_url.GmiBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.AuthenticationGIM;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Ex04 extends GmiBankBaseUrl {


    //https://www.gmibank.com/api/tp-customers/114351";
    // Matcher Class ile müşteri bilgilerini doğrulayın
    //JsonPath ile müşteri bilgilerini doğrulayınız.

    @Test
    public void test04() {

        //Set the Url
        spec.pathParam("first",114351);

        //Set the Expected Data

        //Send the Request and get the Response

        Response response=given().spec(spec).headers("Authorization","Bearer "+ AuthenticationGIM.generateToken()).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion
        // 1.Yol: Matcher Class ile müşteri bilgilerini doğrulayın
        response.then().body("firstName",equalTo("Della"),
                "lastName",equalTo("Heaney"),
                "email",equalTo("ricardo.larkin@yahoo.com"),
                 "mobilePhoneNumber",equalTo("123-456-7893"),
               "country.name",equalTo("USA"),
                "accounts[0].id",equalTo(2333));


        //2.Yol: JsonPath ile müşteri bilgilerini doğrulayınız.
        JsonPath json=response.jsonPath();
        assertEquals("Della",json.getString("firstName"));
        assertEquals("Heaney",json.getString("lastName"));
        assertEquals("ricardo.larkin@yahoo.com",json.getString("email"));
        assertEquals("123-456-7893",json.getString("mobilePhoneNumber"));
        assertEquals("USA",json.getString("country.name"));
        assertEquals(2333,json.getInt("accounts[0].id"));

    }
}
