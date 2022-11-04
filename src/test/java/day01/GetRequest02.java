package day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest02 {

    @Test
    public void test02() {

        String url = "https://reqres.in/api/users/1";
        Response response = given().when().get(url);
        //given().when().get(url) -> request
        //Response response -> response
        response.prettyPrint();

        //Header Test
        response.then().assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType(ContentType.JSON);

        //Body Test
        /* idsi 1 olanın datalarınının aşağıdaki gibi olduğunu test ediniz
        "email": "george.bluth@reqres.in",
        "first_name": "George",
        "last_name": "Bluth",
        "avatar": "https://reqres.in/img/faces/1-image.jpg"
         */





    }
}