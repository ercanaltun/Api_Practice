package examples;

import base_url.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Ex02 extends ReqresBaseUrl {

    //"https://reqres.in/api/users/1";
    //statusCode(200)
    //statusLine("HTTP/1.1 200 OK")
    //contentType(ContentType.JSON);

        /* Matchers ile dataları doğrulayınız
             "id": 5,
            "email": "charles.morris@reqres.in",
            "first_name": "Charles",
            "last_name": "Morris",
            "avatar": "https://reqres.in/img/faces/5-image.jpg"
     */

    @Test
    public void test02() {

        //Set the Url
        spec.pathParams("first","users","second",5);

        //Set the Expected Data

        //Send the Request and get the Response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        //1.Yol:Matcher class ile ==>equalTo() methodu ile
        response.then().assertThat().body("data.id",equalTo(5),
                "data.email",equalTo("charles.morris@reqres.in"),
               "data.first_name",equalTo("Charles"),
                "data.last_name",equalTo("Morris"),
                "data.avatar",equalTo("https://reqres.in/img/faces/5-image.jpg"));


        //2.Yol:JsonPath class'ından obje olusturarak
        JsonPath json=response.jsonPath();
        assertEquals("charles.morris@reqres.in",json.getString("data.email"));
        assertEquals("Charles",json.getString("data.first_name"));
        assertEquals("Morris",json.getString("data.last_name"));
        assertEquals("https://reqres.in/img/faces/5-image.jpg",json.getString("data.avatar"));



    }
}
