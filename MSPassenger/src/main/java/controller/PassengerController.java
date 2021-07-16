package controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Random;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.Passenger;
import service.*;

@Consumes("application/json")
@Produces("application/json")
@Path("/passengers")
public class PassengerController {
	@EJB private BranchLocal branch;
	private HashMap<String, Passenger> passengers = new HashMap<>(); 
	
	public PassengerController() {
		super();
	}
	
	@POST
	@Path("/createPassenger")
	public Response createPassenger(String username, String email, String password, String name, String surname, int age){
		Passenger pass = new Passenger();
		URI uri = null;
		try {
			//To implement security control
			String id = "PASS";
			Random rd = new Random();
			int randInt = rd.nextInt();
			id += Integer.toString(randInt);
			while(passengers.containsKey(id)) {
				id = "PASS";
				randInt = rd.nextInt();
				id += Integer.toString(randInt);
			}
			pass = new Passenger(id, name, surname, age, email);
		}catch(Exception e) {
			String message = "Error encountered while creating new Passenger";
			return Response.serverError().entity(message).build();
		}
		return Response.created(uri).entity(pass).build();
	}
}
