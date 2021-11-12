package db;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

import model.Passenger;

public class MongoConnector {
	
	public MongoConnector() {}
	/**
	 * Get all passenger from MongoDB
	 * @return a List bson.Document
	 */
	public List<Document> getAllPassengers(){
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		List<Document> pass = coll.find().into(new ArrayList<Document>());
		return pass;
	}
	
	/**
	 * Get a passenger by is passengerId from MongoDB
	 * @param passengerId of the passenger to get from the database
	 * @return a bson.Document
	 */
	public Document getPassengerById(String passengerId) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		System.err.println("Request arrived on Mongo Connectori");
		return coll.find(Filters.eq("passengerId", passengerId)).first();
	}
	
	/**
	 * Get a passenger by is email address from MongoDB
	 * @param email of the passenger to get from the database
	 * @return a bson.Document
	 */
	public Document getPassengerByEmail(String email) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		return coll.find(Filters.eq("email", email)).first();
	}
	
	/**
	 * Get all passengers by given name from MongoDB
	 * @param name of the passengers to get from the database
	 * @return a List of bson.Document
	 */
	public List<Document> getPassengerByName(String name) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		return coll.find(Filters.eq("name", name)).into(new ArrayList<Document>());
	}
	
	/**
	 * Get all passengers by given name from MongoDB
	 * @param surname of the passengers to get from the database
	 * @return a List of bson.Document
	 */
	public List<Document> getPassengerBySurname(String surname) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		return coll.find(Filters.eq("surname", surname)).into(new ArrayList<Document>());
	}
	
	/**
	 * Get all passengers by given age from MongoDB
	 * @param age of the passengers to get from the database
	 * @return a List of bson.Document
	 */
	public List<Document> getPassengerByAge(int age) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		return coll.find(Filters.eq("age", age)).into(new ArrayList<Document>());
	}
	
	/**
	 * Get all passengers by age greater then the parameter from MongoDB
	 * @param age of the passengers to get from the database
	 * @return a List of bson.Document
	 */
	public List<Document> getPassengerByAgeGT(int age) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		return coll.find(Filters.gt("age", age)).into(new ArrayList<Document>());
	}
	
	/**
	 * Get all passengers by age less then the parameter from MongoDB
	 * @param age of the passengers to get from the database
	 * @return a List of bson.Document
	 */
	public List<Document> getPassengerByAgeLT(int age) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		return coll.find(Filters.lt("age", age)).into(new ArrayList<Document>());
	}
	
	/**
	 * Create a new Passenger on MongoDB
	 * @param p the Passenger to be saved on the database
	 */
	public void createPassenger(Passenger p) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		Document pass = Passenger.encodePassenger(p);
		coll.insertOne(pass);
	}
	
	/**
	 * Remove a Passenger form MongoDB 
	 * @param passengerId of the passenger to be deleted from the database
	 * @return the result of the operation (true if succeded, false if failed)
	 */
	public boolean removePassenger(String passengerId) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		if(coll.find(Filters.eq("passengerId", passengerId)).first() != null) {
			DeleteResult result = coll.deleteOne(Filters.eq("passengerId", passengerId));
			return result.wasAcknowledged();
		}
		return false;
		
	}
	
	/**
	 * Get the number of passenger stored inside MongoDB
	 * @return the number of passenger stored inside the database
	 */
	public int passengerCount() {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		return (int)coll.count();
	}
	
	public void close() {
		this.m.close();
	}
	
	private final MongoClient m = new MongoClient("132.121.170.248",31183);
}
