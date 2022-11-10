package day04;

import base_url.GmiBankBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.CountryPost;


import static io.restassured.RestAssured.given;
import static utilities.Authentication.generateToken;

public class PostRequest extends GmiBankBaseUrl {
    /*
    https://www.gmibank.com/api/tp-countries adrresine yeni bir ülke ekleyin
    */

    @Test
    public void test10(){
        spec.pathParam("first", "tp-countries");

        CountryPost countryPost = new CountryPost("Batch81");
        System.out.println("countryPost = " + countryPost);

        Response response = given().contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + generateToken())
                .spec(spec).when().body(countryPost)
                .post("/{first}");

        response.prettyPrint();

        CountryPost actualData = response.as(CountryPost.class);
        System.out.println("actualData = " + actualData);

        //Doğrulama Yaptık
        Assert.assertEquals(countryPost.getName(), actualData.getName());
    }
}