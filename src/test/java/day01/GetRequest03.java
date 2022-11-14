package day01;

import base_url.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest03 extends ReqresBaseUrl {

    //https://reqres.in/api/users/5

    /* Matchers ile dataları doğrulayınız
             "id": 5,
            "email": "charles.morris@reqres.in",
            "first_name": "Charles",
            "last_name": "Morris",
            "avatar": "https://reqres.in/img/faces/5-image.jpg"
     */


    @Test
    public void get03() {

        spec.pathParams("first","users","second",5);

        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        response.then().assertThat().body("data.id",equalTo(5),
                "data.email",equalTo("charles.morris@reqres.in"),
                "data.first_name",equalTo("Charles"),
                "data.last_name",equalTo("Morris"),
                "data.avatar",equalTo("https://reqres.in/img/faces/5-image.jpg"));
    }
}