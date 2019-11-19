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
package at.racemanager.drivers.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.racemanager.drivers.api.model.Driver;
import at.racemanager.drivers.logic.DriverService;

/**
 * resource for race drivers
 *
 * @author rolhai
 */
@Path("drivers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DriverResource {

    @Inject
    DriverService service;

    @GET
    public Response getDrivers() {
        return Response.ok(service.getAll()).build();
    }

    @GET
    @Path("{driverId}")
    public Response getDriver(@PathParam(value = "driverId") long driverId) {
        return Response.ok(service.get(driverId)).build();
    }

    @POST
    public Response createDriver(Driver driver) {
        Driver result = service.create(driver);
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @PATCH
    @Path("{driverId}")
    public Response updateDriver(@PathParam(value = "driverId") long driverId, Driver driver) {
        Driver result = service.update(driverId, driver);
        return Response.ok(result).build();
    }

    @PUT
    @Path("{driverId}")
    public Response saveDriver(@PathParam(value = "driverId") long driverId, Driver driver) {
        Driver result = service.save(driverId, driver);
        return Response.ok(result).build();
    }

    @DELETE
    @Path("{driverId}")
    public Response removeDriver(@PathParam(value = "driverId") long driverId) {
        service.remove(driverId);
        return Response.ok().build();
    }
}