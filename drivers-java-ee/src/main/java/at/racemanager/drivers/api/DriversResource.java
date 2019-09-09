package at.racemanager.drivers.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.racemanager.drivers.business.DriverService;

@Path("/drivers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DriversResource {
	
	@Inject
	private DriverService driverService;

    @GET   
    public Response getDrivers() {
        return Response.ok(driverService.getDrivers()).build();
    }
}