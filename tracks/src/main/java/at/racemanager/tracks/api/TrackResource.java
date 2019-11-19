package at.racemanager.tracks.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.racemanager.tracks.api.model.Track;
import at.racemanager.tracks.logic.TrackRepository;

/**
 * resource for race tracks
 *
 * @author rolhai
 */
@Path("tracks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TrackResource {

    @Inject
    TrackRepository repo;

    @GET
    public Response getTracks() {
        return Response.ok(repo.getAll()).build();
    }

    @POST
    public Response createTrack(Track entity) {
        Track result = repo.insert(entity);
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @PUT
    @Path("{trackId}")
    public Response saveDriver(@PathParam(value = "trackId") long trackId, Track entity) {
        if (trackId < 1) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Track result = null;
        Track found = repo.get(trackId);
        if (found == null) {
            result = repo.insert(entity);
        }
        else {
            result = repo.update(entity);
        }
        return Response.ok(result).build();
    }

    @DELETE
    @Path("{trackId}")
    public Response removeTrack(@PathParam(value = "trackId") long trackId) {
        if (trackId < 1) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Track entity = repo.get(trackId);
        repo.remove(entity);
        return Response.ok().build();
    }
}