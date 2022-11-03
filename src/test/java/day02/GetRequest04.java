package day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest04 {

    /*
    http://dummy.restapiexample.com/api/v1/employees  url’ine
GET request’i yolladigimda gelen response’un
status kodunun 200 ve content type’inin “application/json”
ve employees sayisinin 24
ve employee’lerden birinin “Ashton Cox”
ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin.
     */

    @Test
    public void get04() {

        //1.Step: Set the Url

        String url="http://dummy.restapiexample.com/api/v1/employees";

        //2.Step:Set the Data

        //3.Step:Get the Request and Sen the Response
        Response response=given().when().get(url);

        //Do Assertion
        //status kodunun 200 ve content type’inin “application/json” oldugunu dogrulama
        response.then().assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200);

        //ve employees sayisinin 24
        //ve employee’lerden birinin “Ashton Cox”
        //ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin.

        response.then().assertThat().body("data",hasSize(24)
                 ,"data.employee_name",hasItem("Ashton Cox")
                 ,"data.employee_age",hasItems(21,61,23));

        //hasSize(): Datanın size'ını doğrulamak için kullanılır.
        //hasItem(): Girilen tek bir data'yı doğrulamak için kullanılır.
        //hasItems(): Girilen birden fazla datayı doğrulamak için kullanılır.










    }
}
