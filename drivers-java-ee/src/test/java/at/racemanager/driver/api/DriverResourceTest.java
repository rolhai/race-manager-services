/**
 * Copyright (c) 2019 by rolhai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.racemanager.driver.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDate;
import java.time.Month;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

import at.racemanager.drivers.api.model.Driver;
import at.racemanager.drivers.logic.DriverService;
import io.quarkus.test.junit.QuarkusTest;

/**
 * testing the resource driver
 *
 * @author rolhai
 */
@QuarkusTest
public class DriverResourceTest {

    @Inject
    DriverService driverService;

    /**
     * test getting all drivers
     */
    @Test
    public void testGetDriversEndpoint() {
        Jsonb jsonb = JsonbBuilder.create();
        String json = jsonb.toJson(driverService.getAll());

        given().when().get("/drivers").then().statusCode(200).body(is(json));
    }

    /**
     * test adding a driver
     */
    @Test
    public void testAddDriversEndpoint() {
        Driver driver = new Driver();
        driver.setFirstname("Fernando");
        driver.setLastname("Alonso");
        driver.setBirthday(LocalDate.of(1980, Month.JANUARY, 1));

        Jsonb jsonb = JsonbBuilder.create();
        String json = jsonb.toJson(driver);

        given().body(json).header("Content-Type", MediaType.APPLICATION_JSON).when().post("/drivers").then()
        .statusCode(201);
    }

    /**
     * test removing a driver
     */
    @Test
    public void testRemoveDriversEndpoint() {
        given().header("Content-Type", MediaType.APPLICATION_JSON).when().delete("/drivers/"+1).then()
        .statusCode(200);
    }

}