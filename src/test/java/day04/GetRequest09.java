package day04;

import base_url.GmiBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Country;
import pojos.Customer;
import pojos.User;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utilities.AuthenticationGIM.generateToken;

public class GetRequest09 extends GmiBankBaseUrl {

    /*
http://www.gmibank.com/api/tp-customers/110452

    /*
    {
    “id”: 110452,
    “firstName”: “Jasmine”,
    “lastName”: “Stehr”,
    “middleInitial”: “V”,
    “email”: “marni.zboncak@yahoo.com”,
    “mobilePhoneNumber”: “463-609-2097”,
    “phoneNumber”: “1-112-497-0270”,
    “zipCode”: “16525”,
    “address”: “14387 Al Ridge5343 Bert Burgs”,
    “city”: “Waltermouth”,
    “ssn”: “761-59-2911”,
    “createDate”: “2021-11-28T21:00:00Z”,
    “zelleEnrolled”: false,
    “country”: {
        “id”: 3,
        “name”: “USA”,
        “states”: null
    },
    “state”: “California”,
    “user”: {
        “id”: 110016,
        “login”: “leopoldo.reinger”,
        “firstName”: “Jasmine”,
        “lastName”: “Stehr”,
        “email”: “marni.zboncak@yahoo.com”,
        “activated”: true,
        “langKey”: “en”,
        “imageUrl”: null,
        “resetDate”: null
    },
“accounts”: [
        {
            “id”: 107250,
            “description”: “New Account_6thGenQA_01",
            “balance”: 11190,
            “accountType”: “CHECKING”,
            “accountStatusType”: “ACTIVE”,
            “createDate”: “2021-11-24T23:00:00Z”,
            “closedDate”: “2022-11-24T23:00:00Z”,
            “employee”: null,
            “accountlogs”: null
        }
     */


    @Test
    public void test09() {

        //Set the url
        spec.pathParams("first","tp-customers","second",110452);

        //EXPECTED DATA
        User user = new User(110016,"leopoldo.reinger", "Jasmine", "Stehr",
                "marni.zboncak@yahoo.com", true, "en", null, null);

        Country country = new Country(3, "USA", null);

        Customer expectedData = new Customer(110452, "Jasmine", "Stehr", "V", "marni.zboncak@yahoo.com"
                , "463-609-2097", "1-112-497-0270", "16525", "14387 Al Ridge5343 Bert Burgs","Waltermouth"
                , "761-59-2911", "2021-11-28T21:00:00Z", false, country, "California", user);
        System.out.println("expectedData = " + expectedData);


        //Send the Request and Get the Response
        Response response=given().spec(spec).headers("Authorization","Bearer "+generateToken())
                .when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion

        Customer actualData=response.as(Customer.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getId(),actualData.getId());
        assertEquals(expectedData.getFirstName(),actualData.getFirstName());
        assertEquals(expectedData.getLastName(),actualData.getLastName());
        assertEquals(expectedData.getMiddleInitial(),actualData.getMiddleInitial());
        assertEquals(expectedData.getEmail(),actualData.getEmail());
        assertEquals(expectedData.getMobilePhoneNumber(),actualData.getMobilePhoneNumber());
        assertEquals(expectedData.getPhoneNumber(),actualData.getPhoneNumber());
        assertEquals(expectedData.getZipCode(),actualData.getZipCode());
        assertEquals(expectedData.getAddress(),actualData.getAddress());
        assertEquals(expectedData.getCity(),actualData.getCity());
        assertEquals(expectedData.getSsn(),actualData.getSsn());
        assertEquals(expectedData.getCreateDate(),actualData.getCreateDate());
        assertEquals(expectedData.isZelleEnrolled(),actualData.isZelleEnrolled());
        assertEquals(country.getId(),actualData.getCountry().getId());
        assertEquals(country.getName(),actualData.getCountry().getName());
        assertEquals(country.getStates(),actualData.getCountry().getStates());
        assertEquals(expectedData.getState(),actualData.getState());
        assertEquals(user.getId(),actualData.getUser().getId());
        assertEquals(user.getLogin(),actualData.getUser().getLogin());
        assertEquals(user.getFirstName(),actualData.getUser().getFirstName());
        assertEquals(user.getLastName(),actualData.getUser().getLastName());
        assertEquals(user.getEmail(),actualData.getUser().getEmail());
        assertEquals(user.getActivated(),actualData.getUser().getActivated());
        assertEquals(user.getLangKey(),actualData.getUser().getLangKey());
        assertEquals(user.getImageUrl(),actualData.getUser().getImageUrl());
        assertEquals(user.getResetDate(),actualData.getUser().getResetDate());

        //Objectmapper kullanarak:
        spec.pathParams("first","tp-customers","second",110452);

        Customer expectedData2= new Customer(110452, "Jasmine", "Stehr",
                "V", "marni.zboncak@yahoo.com"
                , "463-609-2097", "1-112-497-0270",
                "16525", "14387 Al Ridge5343 Bert Burgs","Waltermouth"
                , "761-59-2911", "2021-11-28T21:00:00Z",
                false, country, "California", user);

        Response response1=given().spec(spec).headers("Authorization","Bearer "+generateToken())
                .when().get("/{first}/{second}");

        Customer actualData2=response1.as(Customer.class);

        assertEquals(expectedData2.getId(),actualData2.getId());
        assertEquals(expectedData2.getUser(),actualData2.getUser());
        assertEquals(expectedData2.getFirstName(),actualData2.getFirstName());
        assertEquals(expectedData2.getLastName(),actualData2.getLastName());





    }


}

