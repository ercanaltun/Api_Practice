package day03;

import base_url.GmiBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GmiBank9DataPojo;
import test_data.GmiBank_TestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static utilities.Authentication.generateToken;

public class GetRequest08 extends GmiBankBaseUrl {

    //http://www.gmibank.com/api/tp-customers/43703
    //          “firstName”: “Alda”,
    //          “lastName”: “Monahan”,
    //          “middleInitial”: “Nichelle Hermann Kohler”,
    //          “email”: “com.github.javafaker.Name@7c011174@gmail.com”,
    //          “mobilePhoneNumber”: “909-162-8114”,
    //          “city”: “St Louis”,
    //          “ssn”: “108-53-6655"
    //          1) MATCHERS CLASS
    //          2) JSON PATH
    //          3) De-Serialization


    @Test
    public void test08() {

        //Set the Url
        spec.pathParams("first", "tp-customers", "second", 43703);

        //Set the Excepted Data

        //Send the Request and Get the Response
        Response response = given().spec(spec).headers("Authorization", "Bearer " + generateToken()).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Asssertion
        //1.Olarak Matchers methodu ile:
        response.then().assertThat()
                .body("firstName", equalTo("Alda"),
                        "lastName", equalTo("Monahan"),
                        "middleInitial", equalTo("Nichelle Hermann Kohler"),
                        "email", equalTo("com.github.javafaker.Name@7c011174@gmail.com"),
                        "mobilePhoneNumber", equalTo("909-162-8114"),
                        "phoneNumber", equalTo("231-501-9849"),
                        "zipCode", equalTo("67321"),
                        "city", equalTo("St Louis"),
                        "ssn", equalTo("108-53-6655"));

        //2.Olarak JsonPath() classı ile:
        JsonPath json = response.jsonPath();
        assertEquals("Alda", json.getString("firstName"));
        assertEquals("Monahan", json.getString("lastName"));
        assertEquals("Nichelle Hermann Kohler", json.getString("middleInitial"));
        assertEquals("com.github.javafaker.Name@7c011174@gmail.com", json.getString("email"));
        assertEquals("909-162-8114", json.getString("mobilePhoneNumber"));
        assertEquals("231-501-9849", json.getString("phoneNumber"));
        assertEquals("67321", json.getString("zipCode"));
        assertEquals("St Louis", json.getString("city"));
        assertEquals("108-53-6655", json.getString("ssn"));


        //3.Olarak Map ile yapma:

        spec.pathParams("first", "tp-customers", "second", 43703);

        GmiBank_TestData objeGmi = new GmiBank_TestData();
        Map expectedDataMap = objeGmi.gmiBankMethodMap("Alda", "Monahan", "Nichelle Hermann Kohler", "com.github.javafaker.Name@7c011174@gmail.com",
                "909-162-8114", "231-501-9849", "67321", "St Louis", "108-53-6655");
        System.out.println("expectedDataMap = " + expectedDataMap);

        Response response1 = given().spec(spec).headers("Authorization", "Bearer " + generateToken()).when().get("/{first}/{second}");

        Map actualDataMap = response1.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        assertEquals(200, response.statusCode());
        assertEquals(expectedDataMap.get("firstName"), actualDataMap.get("firstName"));
        assertEquals(expectedDataMap.get("lastName"), actualDataMap.get("lastName"));
        assertEquals(expectedDataMap.get("middleInitial"), actualDataMap.get("middleInitial"));
        assertEquals(expectedDataMap.get("email"), actualDataMap.get("email"));
        assertEquals(expectedDataMap.get("mobilePhoneNumber"), actualDataMap.get("mobilePhoneNumber"));
        assertEquals(expectedDataMap.get("phoneNumber"), actualDataMap.get("phoneNumber"));
        assertEquals(expectedDataMap.get("zipCode"), actualDataMap.get("zipCode"));
        assertEquals(expectedDataMap.get("city"), actualDataMap.get("city"));
        assertEquals(expectedDataMap.get("ssn"), actualDataMap.get("ssn"));


        //4.Olarak Pojo ile yapma:
        spec.pathParams("first", "tp-customers", "second", 43703);

        GmiBank9DataPojo expectedDataPojo = new GmiBank9DataPojo("Alda", "Monahan",
                "Nichelle Hermann Kohler", "com.github.javafaker.Name@7c011174@gmail.com",
                "909-162-8114", "231-501-9849",
                "67321", "St Louis", "108-53-6655");

        System.out.println("expectedDataPojo = " + expectedDataPojo);

        Response response2 = given().spec(spec).headers("Authorization", "Bearer " + generateToken()).when().get("/{first}/{second}");

        GmiBank9DataPojo actualDataPojo = response2.as(GmiBank9DataPojo.class);
        System.out.println("actualDataPojo = " + actualDataPojo);

        assertEquals(200, response2.statusCode());
        assertEquals(expectedDataPojo.getFirstName(), actualDataPojo.getFirstName());
        assertEquals(expectedDataPojo.getLastName(), actualDataPojo.getLastName());
        assertEquals(expectedDataPojo.getMiddleInitial(), actualDataPojo.getMiddleInitial());
        assertEquals(expectedDataPojo.getEmail(), actualDataPojo.getEmail());
        assertEquals(expectedDataPojo.getMobilePhoneNumber(), actualDataPojo.getMobilePhoneNumber());
        assertEquals(expectedDataPojo.getPhoneNumber(), actualDataPojo.getPhoneNumber());
        assertEquals(expectedDataPojo.getZipCode(), actualDataPojo.getZipCode());
        assertEquals(expectedDataPojo.getCity(), actualDataPojo.getCity());
        assertEquals(expectedDataPojo.getSsn(), actualDataPojo.getSsn());



    }




}
