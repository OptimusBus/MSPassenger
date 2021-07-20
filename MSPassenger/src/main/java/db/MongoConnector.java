package db;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import model.Passenger;

public class MongoConnector {
	private MongoClient m = new MongoClient("172.18.10.144", 31181);
	
	public MongoConnector() {}
	
	public List<Document> getAllPassengers(){
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		List<Document> pass = coll.find().into(new ArrayList<Document>());
		return pass;
	}
	
	public Document getPassengerById(String passengerId) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		return coll.find(Filters.eq("passengerId", passengerId)).first();
	}
	
	public Document getPassengerByEmail(String email) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		return coll.find(Filters.eq("email", email)).first();
	}
	
	public List<Document> getPassengerByName(String name) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		return coll.find(Filters.eq("name", name)).into(new ArrayList<Document>());
	}
	
	public List<Document> getPassengerBySurname(String surname) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		return coll.find(Filters.eq("surname", surname)).into(new ArrayList<Document>());
	}
	
	public List<Document> getPassengerByAge(int age) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		return coll.find(Filters.eq("age", age)).into(new ArrayList<Document>());
	}
	
	public List<Document> getPassengerByAgeGT(int age) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		return coll.find(Filters.gt("age", age)).into(new ArrayList<Document>());
	}
	
	public List<Document> getPassengerByAgeLT(int age) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		return coll.find(Filters.lt("age", age)).into(new ArrayList<Document>());
	}
	
	public void addPassenger(Passenger p) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		Document pass = new Document();
		pass.append("passengerId", p.getPassengerId());
		pass.append("name", p.getName());
		pass.append("surname", p.getSurname());
		pass.append("age", p.getAge());
		pass.append("email",p.getEmail());
		coll.insertOne(pass);
	}
	
	public int passengerCount() {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		return (int)coll.count();
	}
	
	public void close() {
		this.m.close();
	}
}
