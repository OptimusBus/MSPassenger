package controller;

import java.net.URI;
import java.util.HashMap;

//import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.Passenger;
import service.BranchLocal;

@Consumes("application/json")
@Produces("application/json")
@Path("/passengers")
public class PassengerController {
	private BranchLocal branch;
	public PassengerController() {
		super();
	}
	
	@POST
	@Path("/createPassenger")
	public Response createPassenger(String name, String surname, int age, String email){
		String message = "";
		//URI uri = null;
		try {
			message = branch.createPassenger(name, surname, age, email);
		}catch(Exception e) {
			message = "Error while creating new Passenger";
			Response.serverError().entity(message).build();
		}
		return Response.ok().entity(message).build();
	}
	
	@GET
	@Path("/{passengerId}")
	public Response getPassenger(@PathParam("passengerId")String passengerId) {
		Passenger p = branch.getPassengerById(passengerId);
		return Response.ok().entity(p).build();
	}
}
