package at.racemanager.seasons.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.bson.types.ObjectId;

import at.racemanager.seasons.api.model.Driver;
import at.racemanager.seasons.api.model.Season;
import at.racemanager.seasons.api.model.Team;
import at.racemanager.seasons.logic.SeasonService;

/**
 * resource for race seasons with teams and drivers
 *
 * @author rolhai
 */
@Path("/seasons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SeasonResource {

    @Inject
    SeasonService seasonService;

    @GET
    public Response getSeasons() {
        List<Season> result = seasonService.getSeasons();
        return Response.ok(result).build();
    }

    @GET
    @Path("teams")
    public Response getTeams() {
        List<Team> result = seasonService.getTeams();
        return Response.ok(result).build();
    }

    @GET
    @Path("drivers")
    public Response getDrivers() {
        List<Driver> result = seasonService.getDrivers();
        return Response.ok(result).build();
    }

    @POST
    public Response addSeason(Season season) {
        Season result = seasonService.addSeason(season);
        return Response.status(Status.CREATED).entity(result).build();
    }

    @POST
    @Path("{seasonId}/teams")
    public Response addTeam(@PathParam("seasonId") ObjectId seasonId, Team team) {
        Team result = seasonService.addTeam(seasonId, team);
        return Response.status(Status.CREATED).entity(result).build();
    }

    @POST
    @Path("{seasonId}/teams/{teamId}/drivers")
    public Response addDriver(@PathParam("seasonId") ObjectId seasonId, @PathParam("teamId") ObjectId teamId, Driver driver) {
        Driver result = seasonService.addDriver(seasonId, teamId, driver);
        return Response.status(Status.CREATED).entity(result).build();
    }

    @DELETE
    @PathParam("{seasonId}")
    public Response deleteSeason(@PathParam("seasonId") ObjectId seasonId) {
        seasonService.deleteSeason(seasonId);
        return Response.ok().build();
    }

    @DELETE
    @Path("{seasonId}/teams/{teamId}")
    public Response deleteTeam(@PathParam("seasonId") ObjectId seasonId, @PathParam("teamId") ObjectId teamId) {
        seasonService.deleteTeam(seasonId, teamId);
        return Response.ok().build();
    }

    @DELETE
    @Path("{seasonId}/teams/{teamId}/drivers/{driverId}")
    public Response deleteDriver(@PathParam("seasonId") ObjectId seasonId, @PathParam("teamId") ObjectId teamId, @PathParam("driverId") ObjectId driverId) {
        seasonService.deleteDriver(seasonId, teamId, driverId);
        return Response.ok().build();
    }
}