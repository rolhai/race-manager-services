package at.racemanager.tracks.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.racemanager.tracks.api.model.Track;
import at.racemanager.tracks.logic.TrackService;

/**
 * resource for tracks
 *
 * @author rolhai
 */
@Path("tracks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TrackResource {

    @Inject
    TrackService service;

    @GET
    public Response getTracks() {
        return Response.ok(service.getTracks()).build();
    }

    @PUT
    public Response updateTrack(Track obj) {
        service.update(obj);
        return Response.ok().build();
    }

    @DELETE
    public Response removeTrack(Track obj) {
        service.remove(obj);
        return Response.ok().build();
    }
}