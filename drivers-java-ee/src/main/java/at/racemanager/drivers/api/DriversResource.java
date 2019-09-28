package at.racemanager.drivers.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	DriverService driverService;

    @GET   
    public Response getDrivers() {
        return Response.ok(driverService.getDrivers()).build();
    }
    
    @POST
    public Response addDriver(Driver driver) {
    	driverService.add(driver);
    	return Response.ok().build();
    }
    
    @DELETE
    public Response removeDriver(Driver driver) {
    	driverService.remove(driver);
    	return Response.ok().build();
    }
}