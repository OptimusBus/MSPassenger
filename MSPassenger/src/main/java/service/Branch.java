package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.bson.Document;

import db.MongoConnector;
import model.Passenger;

@Stateless
public class Branch implements BranchLocal {
	
	private MongoConnector mdb = new MongoConnector();
	public Branch(){}
	
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
		mdb.addPassenger(p);
		return p.getPassengerId();
	}

	public List<Passenger> getAllPassenger() {
		List<Document> doc = mdb.getAllPassengers();
		if(doc.isEmpty()) {
			System.err.println("No element found");
			return null;
		}
		return this.convertDocumentList(doc);
	}

	public Passenger getPassengerById(String passengerId) {
		Document d = mdb.getPassengerById(passengerId);
		if(d != null) {
			return this.convertDocumentToPassenger(d);
		}else {
			System.err.println("No passenger with id " + passengerId + " found");
		}
		return null;
	}

	public Passenger getPassengerByEmail(String email) {
		Document d = mdb.getPassengerByEmail(email);
		if(d != null) {
			return this.convertDocumentToPassenger(d);
		}else {
			System.err.println("No passenger with email " + email + " found");
		}
		return null;
	}

	public List<Passenger> getPassengerByName(String name) {
		List<Document> doc = mdb.getPassengerByName(name);
		if(doc.isEmpty()) {
			System.err.println("No element found");
			return null;
		}
		return this.convertDocumentList(doc);
	}

	public List<Passenger> getPassengerBySurname(String surname) {
		List<Document> doc = mdb.getPassengerBySurname(surname);
		if(doc.isEmpty()) {
			System.err.println("No element found");
			return null;
		}
		return this.convertDocumentList(doc);
	}
	
	public List<Passenger> getPassengerByAge(String age) {
		int a = Integer.parseInt(age);
		List<Document> doc = mdb.getPassengerByAge(a);
		if(doc.isEmpty()) {
			System.err.println("No element found");
			return null;
		}
		return this.convertDocumentList(doc);
	}
	
	public List<Passenger> getPassengerByAgeGT(String age) {
		int a = Integer.parseInt(age);
		List<Document> doc = mdb.getPassengerByAgeGT(a);
		if(doc.isEmpty()) {
			System.err.println("No element found");
			return null;
		}
		return this.convertDocumentList(doc);
	}
	
	public List<Passenger> getPassengerByAgeLT(String age) {
		int a = Integer.parseInt(age);
		List<Document> doc = mdb.getPassengerByAgeLT(a);
		if(doc.isEmpty()) {
			System.err.println("No element found");
			return null;
		}
		return this.convertDocumentList(doc);
	}
	
	public List<Passenger> convertDocumentList(List<Document> doc){
		List<Passenger> passengers = new ArrayList<Passenger>();		
		for (Document d : doc) {
			passengers.add(this.convertDocumentToPassenger(d));
		}
		return passengers;
	}
	
	public Passenger convertDocumentToPassenger(Document d) {
		String passengerId = d.getString("passengerId");
		String name = d.getString("name");
		String surname = d.getString("surname");
		int age;
		try {
			String temp = d.getString("age");
			age = Integer.parseInt(temp);
		}catch(ClassCastException e) {
			try {
				age = d.getInteger("age");
			}catch(ClassCastException e1) {
				age = d.getDouble("age").intValue();
			}
		}
		String email = d.getString("email");
		return new Passenger(passengerId, name, surname, age, email);
	}
	
	public boolean removePassenger(String passengerId) {
		return mdb.removePassenger(passengerId);
	}
	
}
