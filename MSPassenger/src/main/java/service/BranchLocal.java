package service;

import java.util.List;

import javax.ejb.Local;

import model.Passenger;

@Local
public interface BranchLocal {
	public String createPassenger(String name, String surname, int age, String email);
	public List<Passenger> getAllPassenger();
	public Passenger getPassengerById(String passengerId);
	public Passenger getPassengerByEmail(String email);
	public List<Passenger> getPassengerByName(String name);
	public List<Passenger> getPassengerBySurname(String surname);
}
