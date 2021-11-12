package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.bson.Document;

import db.MongoConnector;
import model.Passenger;

@Stateless
public class Branch implements BranchLocal {
	
	public Branch(){}
	
	/**
	 * Create a new Passenger on DB
	 * @param name the name of the passenger
	 * @param surname the surname of the passenger
	 * @param i the age of the passenger (int)
	 * @param email the email of the passenger
	 * @return the passengerId
	 */
	@Override
	public String createPassenger(String name, String surname, int age, String email) {
		Passenger p = new Passenger(name, surname, age, email);
		p.createId(mdb.passengerCount()+1);
		Document temp = mdb.getPassengerById(p.getPassengerId());
		int c = 1;
		while(temp != null) {
			c++;
			p.createId(mdb.passengerCount()+c);
			temp = mdb.getPassengerById(p.getPassengerId());
		}
		mdb.createPassenger(p);
		return p.getPassengerId();
	}
	
	/**
	 * Get all the passengers stored on DB
	 * @return the List of the passengers
	 */
	@Override
	public List<Passenger> getAllPassenger() {
		List<Document> doc = mdb.getAllPassengers();
		if(doc.isEmpty()) {
			System.err.println("No element found");
			return null;
		}
		return this.convertDocumentList(doc);
	}
	
	/**
	 * Get a passenger on database by its passengerId
	 * @param passengerId of the passenger to get from the database
	 * @return the Passenger or null if the passenger is not found
	 */
	@Override
	public Passenger getPassengerById(String passengerId) {
		Document d = mdb.getPassengerById(passengerId);
		if(d != null) {
			return Passenger.decodePassenger(d);
		}else {
			System.err.println("No passenger with id " + passengerId + " found");
		}
		return null;
	}
	
	/**
	 * Get the passenger on the DB by its email address
	 * @param email of the passenger to get from the database
	 * @return the Passenger or null if the passenger is not found
	 */
	@Override
	public Passenger getPassengerByEmail(String email) {
		Document d = mdb.getPassengerByEmail(email);
		if(d != null) {
			return Passenger.decodePassenger(d);
		}else {
			System.err.println("No passenger with email " + email + " found");
		}
		return null;
	}

	/**
	 * Get all passengers from the DB for given name
	 * @param name of the passengers to get from the database
	 * @return the List<Passenger> found or null if there aren't no passenger with that name
	 */
	@Override
	public List<Passenger> getPassengerByName(String name) {
		List<Document> doc = mdb.getPassengerByName(name);
		if(doc.isEmpty()) {
			System.err.println("No element found");
			return null;
		}
		return this.convertDocumentList(doc);
	}

	/**
	 * Get all passengers from the DB for given surname
	 * @param surname of the passengers to get from the database
	 * @return the List<Passenger> found or null if there aren't no passenger with that surname
	 */
	@Override
	public List<Passenger> getPassengerBySurname(String surname) {
		List<Document> doc = mdb.getPassengerBySurname(surname);
		if(doc.isEmpty()) {
			System.err.println("No element found");
			return null;
		}
		return this.convertDocumentList(doc);
	}
	
	/**
	 * Get all passengers from the DB for given age
	 * @param age of the passengers to get from the database
	 * @return the List<Passenger> found or null if there aren't no passenger with that age
	 */
	@Override
	public List<Passenger> getPassengerByAge(String age) {
		int a = Integer.parseInt(age);
		List<Document> doc = mdb.getPassengerByAge(a);
		if(doc.isEmpty()) {
			System.err.println("No element found");
			return null;
		}
		return this.convertDocumentList(doc);
	}
	
	/**
	 * Get all passengers from the DB for age greater then the parameter age
	 * @param age of the passengers to get from the database
	 * @return the List<Passenger> found or null if there aren't no passenger with age greater than the parameter 
	 */
	@Override
	public List<Passenger> getPassengerByAgeGT(String age) {
		int a = Integer.parseInt(age);
		List<Document> doc = mdb.getPassengerByAgeGT(a);
		if(doc.isEmpty()) {
			System.err.println("No element found");
			return null;
		}
		return this.convertDocumentList(doc);
	}
	
	/**
	 * Get all passengers from the DB for age less then the parameter age
	 * @param age of the passengers to get from the database
	 * @return the List<Passenger> found or null if there aren't no passenger with age less than the parameter 
	 */
	@Override
	public List<Passenger> getPassengerByAgeLT(String age) {
		int a = Integer.parseInt(age);
		List<Document> doc = mdb.getPassengerByAgeLT(a);
		if(doc.isEmpty()) {
			System.err.println("No element found");
			return null;
		}
		return this.convertDocumentList(doc);
	}
	
	/**
	 * Remove a passenger from the database
	 * @param passengerId of the passenger to be removed
	 * @return the result of the operation (true if succeded or false if failed)
	 */
	@Override
	public boolean removePassenger(String passengerId) {
		return mdb.removePassenger(passengerId);
	}
	
	/**
	 * Utility function, convert a list of bson.Document in to a list of Passenger
	 * @param doc the List<Document> to be converted
	 * @return a List<Passenger>
	 */
	public List<Passenger> convertDocumentList(List<Document> doc){
		List<Passenger> passengers = new ArrayList<Passenger>();		
		for (Document d : doc) {
			passengers.add(Passenger.decodePassenger(d));
		}
		return passengers;
	}
	
	
	private MongoConnector mdb = new MongoConnector();
}
