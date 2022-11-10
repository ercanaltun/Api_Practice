package test_data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GmiBank_TestData {

    public Map<String, String> gmiBankMethodMap(String firstName, String lastName, String middleInitial, String email,
                String mobilePhoneNumber,String phoneNumber,String zipCode,String city,String ssn ) {
        Map<String, String> dataKeyMap = new HashMap<>();
        dataKeyMap.put("firstName", firstName);
        dataKeyMap.put("lastName", lastName);
        dataKeyMap.put("middleInitial", middleInitial);
        dataKeyMap.put("email", email);
        dataKeyMap.put("mobilePhoneNumber", mobilePhoneNumber);
        dataKeyMap.put("phoneNumber", phoneNumber);
        dataKeyMap.put("zipCode", zipCode);
        dataKeyMap.put("city", city);
        dataKeyMap.put("ssn", ssn);


        return dataKeyMap;
    }


}
