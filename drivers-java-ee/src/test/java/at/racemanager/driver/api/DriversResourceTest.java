/**
 * Copyright (c) 2019 by rolhai
 * All rights reserved.
 */
package at.racemanager.driver.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

import at.racemanager.drivers.api.model.Driver;
import at.racemanager.drivers.logic.DriverService;
import io.quarkus.test.junit.QuarkusTest;

/**
 * testing the resource drivers
 * 
 * @author rolhai
 */
@QuarkusTest
public class DriversResourceTest {

	@Inject
	DriverService driverService;
	
	/**
	 * test getting all drivers
	 */
    @Test
    public void testGetDriversEndpoint() {
    	Jsonb jsonb = JsonbBuilder.create();
    	String json = jsonb.toJson(driverService.getDrivers());  
    	
        given()
          .when().get("/drivers")
          .then().statusCode(200)
          .body(is(json));
    }
    
    /**
     * test adding a driver
     */
    @Test
    public void testAddDriversEndpoint() {
    	Driver driver = new Driver();
    	driver.setFirstname("Fernando");
    	driver.setLastname("Alonso");
    	
    	Jsonb jsonb = JsonbBuilder.create();
    	String json = jsonb.toJson(driver);
    	
    	given()
    		.body(json)
    		.header("Content-Type", MediaType.APPLICATION_JSON)
    		.when().put("/drivers")
    		.then().statusCode(200);
    }
    
    /**
     * test removing a driver
     */
    @Test
    public void testRemoveDriversEndpoint() {
    	Driver driver = new Driver();
    	driver.setFirstname("Fernando");
    	driver.setLastname("Alonso");
    	
    	Jsonb jsonb = JsonbBuilder.create();
    	String json = jsonb.toJson(driver);

    	given()
    		.body(json)
    		.header("Content-Type", MediaType.APPLICATION_JSON)
    		.when().delete("/drivers")
    		.then().statusCode(200);
    }

}