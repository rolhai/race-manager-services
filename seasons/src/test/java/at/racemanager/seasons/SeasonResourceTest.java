package at.racemanager.seasons;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.junit.jupiter.api.Test;

import at.racemanager.seasons.logic.SeasonService;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class SeasonResourceTest {

    @Inject
    SeasonService seasonService;

    @Test
    public void testGetSeasonsEndpoint() {
        Jsonb jsonb = JsonbBuilder.create();
        String json = jsonb.toJson(seasonService.getSeasons());

        given()
        .when().get("/seasons")
        .then().statusCode(200).body(is(json));
    }

}