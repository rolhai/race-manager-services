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
package at.racemanager.tracks.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.junit.jupiter.api.Test;

import at.racemanager.tracks.logic.TrackService;
import io.quarkus.test.junit.QuarkusTest;

/**
 * testing the resource track
 *
 * @author rolhai
 */
@QuarkusTest
public class TrackResourceTest {

    @Inject
    TrackService service;

    /**
     * test getting all drivers
     */
    @Test
    public void testGetDriversEndpoint() {
        Jsonb jsonb = JsonbBuilder.create();
        String json = jsonb.toJson(service.getTracks());

        given().when().get("/tracks").then().statusCode(200).body(is(json));
    }

}