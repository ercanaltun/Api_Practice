package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setup() {
        spec = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1/").build();
    }

}

