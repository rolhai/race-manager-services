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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.racemanager.teams.logic.TeamService;
import at.racemanager.teams.model.Team;

/**
 * resource for teams
 *
 * @author rolhai
 */
@Path("teams")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamsResource {

    @Inject
    TeamService service;

    @GET
    public Response getTeam() {
        return Response.ok(service.getTeams()).build();
    }

    @PUT
    public Response updateTeam(Team obj) {
        service.update(obj);
        return Response.ok().build();
    }

    @DELETE
    public Response removeTrack(Team obj) {
        service.remove(obj);
        return Response.ok().build();
    }
}
