package controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Random;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.Passenger;
import service.*;

@Consumes("application/json")
@Produces("application/json")
@Path("/passengers")
public class PassengerController {
	//@EJB private BranchLocal branch;
	private HashMap<String, Passenger> passengers = new HashMap<String, Passenger>(); 
	
	public PassengerController() {
		super();
	}
	
	@POST
	@Path("/createPassenger")
	public Response createPassenger(String username, String email, String password, String name, String surname, int age){
		return null;
	}
	
	@GET
	@Path("/{passengerId}")
	public Response getPassenger(@PathParam("passengerId")String passengerId) {
		Passenger pass = passengers.get(passengerId);
		return Response.ok().entity(pass).build();
	}
}
