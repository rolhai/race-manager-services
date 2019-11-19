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
package at.racemanager.teams.api;

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

import at.racemanager.teams.logic.TeamRepository;
import at.racemanager.teams.model.Team;

/**
 * resource for teams
 *
 * @author rolhai
 */
@Path("teams")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamResource {

    @Inject
    TeamRepository repo;

    @GET
    public Response getTeam() {
        return Response.ok(repo.getAll()).build();
    }

    @POST
    public Response crateTeam(Team entity) {
        Team result = repo.insert(entity);
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @PUT
    @Path("{teamId}")
    public Response saveDriver(@PathParam(value = "teamId") long teamId, Team entity) {
        if (teamId < 1) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Team result = null;
        Team found = repo.get(teamId);
        if (found == null) {
            result = repo.insert(entity);
        }
        else {
            result = repo.update(entity);
        }
        return Response.ok(result).build();
    }

    @DELETE
    @Path("{teamId}")
    public Response removeTrack(@PathParam(value = "teamId") long teamId) {
        if (teamId < 1) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Team entity = repo.get(teamId);
        repo.remove(entity);
        return Response.ok().build();
    }
}
