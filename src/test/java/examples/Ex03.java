package examples;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Ex03 extends DummyBaseUrl {

        /*
    http://dummy.restapiexample.com/api/v1/employees  url’ine
GET request’i yolladigimda gelen response’un
status kodunun 200 ve content type’inin “application/json”
ve employees sayisinin 24
ve employee’lerden birinin “Ashton Cox”
ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin.
     */

    @Test
    public void test03() {

        //Set the Url
        spec.pathParam("first","employees");

        //Set the Expected Data

        //Send the Request and get the Response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion
        //1.Yol:Matchers class taki equalTo(),hasSize(),hasItem ve hasItems methodları ile
        response.then().statusCode(200).contentType(ContentType.JSON)
                       .body("data.id",hasSize(24),
                        "data.employee_name",hasItem("Ashton Cox"),
                               "data.employee_age",hasItems(21,61,23));

        //2.Yol:JsonPath formatına çevirerek
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        JsonPath json=response.jsonPath();
        assertEquals(24,json.getList("data.findAll{it.id}.id").size());
        assertTrue(json.getString("data.employee_name").contains("Ashton Cox"));
        assertTrue(json.getString("data.employee_age").contains("21"));
        assertTrue(json.getString("data.employee_age").contains("61"));
        assertTrue(json.getString("data.employee_age").contains("23"));

    }











}
