package examples;

import base_url.GmiBankBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Customer;
import utilities.ReadText;
import utilities.WriteToText;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static utilities.AuthenticationGIM.generateToken;

public class Ex05_WriteToText_ReadText extends GmiBankBaseUrl {


     /*
    http://www.gmibank.com/api/tp-customers end point'ine
    request gönderin
     1) Tüm Customer bilgilerini ekrana yazdırın.
     2) Tüm Customer SSN lerini ekrana yazdırın.
     3) Tüm Customer SSN lerini text dosyası olarak kaydedin
     4) Tüm Customer Email lerini ekrana yazdırın.
     5) Tüm customer Email lerini text dosyası olarak kaydedin
     6) Tüm Customer FirstName lerini ekrana yazdırın.
     7) Tüm Customer FirstName lerini text dosyası olarak kaydedin
     8) Tüm Customer City lerini ekrana yazdırın.
     9) Tüm Customer City lerini text dosyası olarak kaydedin
     10) Olusturduğunuz text dosyasından  SSNleri okuyarak
        "531-95-8437", "049-43-2360", "123-34-3434" SSNlerinin olduğunu doğrulayın
     11) Olusturduğunuz text dosyasından  Email leri okuyarak
        "ma34@gmail.com", "demot36@gmail.com", "bg@gmail.com" Emailnlerinin olduğunu doğrulayın
     12) Olusturduğunuz text dosyasından  City leri okuyarak
        "adana", "NewCity", "Sacramento" City lerinin olduğunu doğrulayın


     */


    @Test
    public void test05() throws JsonProcessingException {

        //1.Step:Set the Url
        spec.pathParam("first","tp-customers");

        //2.Step:Set the Expected Data(Payload)


        //3.Step:Send the Request and Get the Response
        Response response=given().spec(spec).headers("Authorization","Bearer "+generateToken()).when().get("/{first}");

        Customer [] customers;
        ObjectMapper obje=new ObjectMapper();
        customers=obje.readValue(response.asString(),Customer[].class);


        //1) Tüm Customer bilgilerini ekrana yazdırın.
        for (int i = 0; i < customers.length; i++) {
         //   System.out.println("customers = " + customers[i]);
        }

        //2) Tüm Customer SSN lerini ekrana yazdırın.
        for (int i = 0; i < customers.length; i++) {
            System.out.println("customers Ssn = " + customers[i].getSsn());
        }

        //3) Tüm Customer SSN lerini text dosyası olarak kaydedin
        String fileNameSSN="src/test/java/examples/SSN.text";
        WriteToText.saveSSNData(fileNameSSN, customers);

        //4) Tüm Customer Email lerini ekrana yazdırın
        for (int i = 0; i < customers.length; i++) {

            System.out.println("customers Emailler = " + customers[i].getEmail());
        }
        //5) Tüm customer Email lerini text dosyası olarak kaydedin

        String fileNameEmail="src/test/java/examples/email.text";
        WriteToText.saveEmailData(fileNameEmail, customers);

        //6) Tüm Customer FirstName lerini ekrana yazdırın.
        for (int i = 0; i < customers.length; i++) {
            System.out.println("customers FirstName = " + customers[i].getFirstName());
        }

        //7) Tüm Customer FirstName lerini text dosyası olarak kaydedin
        String fileNameFirstName="src/test/java/examples/FirstName.text";
        WriteToText.saveFirstNameData(fileNameFirstName, customers);

        //8) Tüm Customer City lerini ekrana yazdırın.
        for (int i = 0; i < customers.length; i++) {
            System.out.println("customers City = " + customers[i].getCity());
        }

        //9) Tüm Customer City lerini text dosyası olarak kaydedin
        String fileNameCity="src/test/java/examples/City.text";
        for (int i = 0; i < customers.length; i++) {
            WriteToText.saveCityData(fileNameCity, customers);
        }

        //10) Olusturduğunuz text dosyasından  SSNleri okuyarak
        //"531-95-8437", "049-43-2360", "123-34-3434" SSNlerinin olduğunu doğrulayın

        List<String> expectedSsn=new ArrayList<>();
        expectedSsn.add("531-95-8437");
        expectedSsn.add("049-43-2360");
        expectedSsn.add("123-34-3434");

        List<String>actualSsn= ReadText.readCustomerSSNList(fileNameSSN);

        assertTrue(actualSsn.containsAll(expectedSsn));

        //11) Olusturduğunuz text dosyasından  Email leri okuyarak
        //"ma34@gmail.com", "demot36@gmail.com", "bg@gmail.com" Emailnlerinin olduğunu doğrulayın
        List<String>expectedEmails=new ArrayList<>();
        expectedEmails.add("ma34@gmail.com");
        expectedEmails.add("demot36@gmail.com");
        expectedEmails.add("bg@gmail.com");

       List<String> actualEmails=ReadText.readCustomerEmailList(fileNameEmail);

       assertTrue(actualEmails.containsAll(expectedEmails));


       // 12) Olusturduğunuz text dosyasından  City leri okuyarak
       // "adana", "NewCity", "Sacramento" City lerinin olduğunu doğrulayın

        List<String>expectedCities=new ArrayList<>();
        expectedCities.add("adana");
        expectedCities.add("NewCity");
        expectedCities.add("Sacramento");

        List<String>actualCities=ReadText.readCustomerCityList(fileNameCity);

        assertTrue(actualCities.containsAll(expectedCities));


    }
}
