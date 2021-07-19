package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Passenger;

public class Branch implements BranchLocal {
	@PersistenceContext
	EntityManager em;
	
	public Branch(){}
	
	@Override
	public String createPassenger(String passengerId, String name, String surname, int age, String email) {
		Passenger p = new Passenger(passengerId, name, surname, age, email);
		em.persist(p);
		return passengerId;
	}

	@Override
	public List<Passenger> getAllPassenger() {
		return em.createNamedQuery("Passenger.getAllPassengers", Passenger.class).getResultList();
	}

	@Override
	public Passenger getPassengerById(String passengerId) {
		return em.find(Passenger.class, passengerId);
	}

	@Override
	public Passenger getPassengerByEmail(String email) {
		return em.find(Passenger.class, email);
	}

	@Override
	public List<Passenger> getPassengerByName(String name) {
		return em.createNamedQuery("Passenger.getAllPassengersByName", Passenger.class).getResultList();
	}

	@Override
	public List<Passenger> getPassengerBySurname(String surname) {
		return em.createNamedQuery("Passenger.getAllPassengersBySurname", Passenger.class).getResultList();
	}
}
