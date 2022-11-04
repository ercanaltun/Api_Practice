package examples;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Ex01 extends ReqresBaseUrl {

    //"https://reqres.in/api/users/1";
    //statusCode(200)
    //statusLine("HTTP/1.1 200 OK")
    //contentType(ContentType.JSON);
          /* idsi 1 olanın datalarınının aşağıdaki gibi olduğunu test ediniz
            "id": 1,
        "email": "george.bluth@reqres.in",
        "first_name": "George",
        "last_name": "Bluth",
        "avatar": "https://reqres.in/img/faces/1-image.jpg"
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"

        "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
          */

    //Set the Url
    //Set the Expected Data
    //Send the Request and get the Response
    //Do Assertion

    @Test
    public void test01() {

        //Set the Url
        spec.pathParams("first", "users", "second", 1);


        //Set the Expected Data

        //Send the Request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        response.then().assertThat().statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                contentType(ContentType.JSON);
        response.then().body(
                "data.email", equalTo("george.bluth@reqres.in"),
                "data.first_name", equalTo("George"),
                "data.last_name", equalTo("Bluth"),
                "data.avatar", equalTo("https://reqres.in/img/faces/1-image.jpg"),
                "support.url", equalTo("https://reqres.in/#support-heading"),
                "support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));


    }


}
