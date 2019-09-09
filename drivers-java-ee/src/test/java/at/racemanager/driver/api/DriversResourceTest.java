package at.racemanager.driver.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.junit.jupiter.api.Test;

import at.racemanager.drivers.business.DriverService;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class DriversResourceTest {

	@Inject
	private DriverService driverService;
	
    @Test
    public void testHelloEndpoint() {
    	Jsonb jsonb = JsonbBuilder.create();
    	String json = jsonb.toJson(driverService.getDrivers());  
    	
        given()
          .when().get("/drivers")
          .then()
             .statusCode(200)
             .body(is(json));
    }

}