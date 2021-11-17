package controller;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.bson.Document;

import com.mongodb.BasicDBObject;

import model.Passenger;
import service.Branch;
import service.BranchLocal;

@Consumes("application/json")
@Produces("application/json")
@Path("/passengers")
public class PassengerController {
	private BranchLocal branch = new Branch();
	public PassengerController() {
		super();
	}
	
	@POST
	@Path("/createPassenger")
	public Response createPassenger(String request){
		Passenger pass = new Passenger();
		BasicDBObject b = BasicDBObject.parse(request);
		Document d = new Document(b);
		if(d.size()<= 0)return  Response.status(500).entity("Empty document").build();
		try {
			pass = branch.createPassenger(d);
			if(pass != null)return Response.ok(new URI("/passengers/"+pass.getPassengerId())).build();
		}catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Error while creating the passenger").build();
		}
		return Response.status(500).entity("Impossible to create passenger").build();
	}
	
	@GET
	@Path("/{passengerId}")
	public Response getPassenger(@PathParam("passengerId")String passengerId) {
		Passenger p = branch.getPassengerById(passengerId);
		if(p != null)return Response.ok(p).build();
		return Response.noContent().entity("No passenger found with id: "+passengerId).build();
	}
	
	@GET
	@Path("/all")
	public Response getAllPassengers() {
		List<Passenger> pass = branch.getAllPassenger();
		if(pass != null)return Response.ok(pass).build();
		return Response.noContent().entity("No passengers found").build();
	}
	
	@GET
	@Path("/getByEmail")
	public Response getPassengersByEmail(@QueryParam("email")String email) {
		Passenger pass = branch.getPassengerByEmail(email);
		if(pass != null)return Response.ok(pass).build();
		return Response.noContent().entity("No passengers found").build();
	}
	
	@GET
	@Path("/getByName")
	public Response getPassengersByName(@QueryParam("name")String name) {
		List<Passenger> pass = branch.getPassengerByName(name);
		if(pass != null)return Response.ok(pass).build();
		return Response.noContent().entity("No passengers found").build();
	}
	
	@GET
	@Path("/getBySurname")
	public Response getPassengersBySurname(@QueryParam("surname")String surname) {
		List<Passenger> pass = branch.getPassengerBySurname(surname);
		if(pass != null)return Response.ok(pass).build();
		return Response.noContent().entity("No passengers found").build();
	}
	
	@GET
	@Path("/getByAge")
	public Response getPassengersByAge(@QueryParam("age")String age) {
		List<Passenger> pass = branch.getPassengerByAge(age);
		if(pass != null)return Response.ok(pass).build();
		return Response.noContent().entity("No passengers found").build();
	}
	
	@GET
	@Path("/getByAgeGT")
	public Response getPassengersByAgeGT(@QueryParam("age")String age) {
		List<Passenger> pass = branch.getPassengerByAgeGT(age);
		if(pass != null)return Response.ok(pass).build();
		return Response.noContent().entity("No passengers found").build();
	}
	
	@GET
	@Path("/getByAgeLT")
	public Response getPassengersByAgeLT(@QueryParam("age")String age) {
		List<Passenger> pass = branch.getPassengerByAgeLT(age);
		if(pass != null)return Response.ok(pass).build();
		return Response.noContent().entity("No passengers found").build();
	}
	
	@GET
	@Path("/getByUsername")
	public Response getPassengersByUsername(@QueryParam("username") String username) {
		Passenger p = branch.getPassengerByUsername(username);
		if(p != null)return Response.ok(p).build();
		return Response.noContent().entity("No passengers found").build();
	}
	
	@GET
	@Path("/getEmail/{passengerId}")
	public Response getEmailFromPassengerId(@PathParam("passengerId") String passengerId) {
		Passenger p = branch.getPassengerById(passengerId);
		if(p != null)return Response.ok(p.getEmail()).build();
		return Response.noContent().entity("No passengers found").build();
	}
	
	@DELETE
	@Path("/removePassenger")
	public Response removePassenger(@QueryParam("passengerId")String passengerId) {
		boolean flag = branch.removePassenger(passengerId);
		if(flag)return Response.ok("Passenger with id: "+passengerId+" is deleted").build();
		return Response.status(500).entity("Impossible to remove the passenger with id: "+passengerId).build();
	}
}
