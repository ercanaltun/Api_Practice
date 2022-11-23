package day03;

import base_url.GmiBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static utilities.AuthenticationGIM.generateToken;

public class GetRequest07 extends GmiBankBaseUrl {

    //http://www.gmibank.com/api/tp-customers/110472 adresindeki müşteri bilgilerini doğrulayın
    //   “firstName”: “Melva”,
    //   “lastName”: “Bernhard”,
    //   “email”: “chas.kuhlman@yahoo.com”
    //   “zipCode”: “40207"
    //   “country” “name”: “San Jose”
    //   “login”: “delilah.metz”


    @Test
    public void test07() {

        //1.Step:Set the Url
        spec.pathParams("first","tp-customers","second",110472);

        //2.Step:Set the Expected Data

        //3.Step:Send the Request and Get the Response
        Response response=given().spec(spec).headers("Authorization","Bearer "+generateToken())
                .when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        response.then().assertThat().body("firstName",equalTo("Melva"),
                "lastName",equalTo("Bernhard"),
                "email",equalTo("chas.kuhlman@yahoo.com"),
                "zipCode",equalTo("40207"),
                "country.name",equalTo("San Jose"),
                "user.login",equalTo("delilah.metz"));

        //JsonPath ile:

        JsonPath json =response.jsonPath();
        assertEquals("Melva",json.getString("firstName"));
        assertEquals("Bernhard",json.getString("lastName"));
        assertEquals("chas.kuhlman@yahoo.com",json.getString("email"));
        assertEquals("40207",json.getString("zipCode"));
        assertEquals("San Jose",json.getString("country.name"));
        assertEquals("delilah.metz",json.getString("user.login"));

    }
}
