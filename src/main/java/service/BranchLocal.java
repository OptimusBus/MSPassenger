package service;

import java.util.List;

import javax.ejb.Local;

import model.Passenger;

@Local
public interface BranchLocal {
	public String createPassenger(String name, String surname, int i, String email);
	public List<Passenger> getAllPassenger();
	public Passenger getPassengerById(String passengerId);
	public Passenger getPassengerByEmail(String email);
	public List<Passenger> getPassengerByName(String name);
	public List<Passenger> getPassengerBySurname(String surname);
	public boolean removePassenger(String passengerId);
	public List<Passenger> getPassengerByAge(String age);
	public List<Passenger> getPassengerByAgeGT(String age);
	public List<Passenger> getPassengerByAgeLT(String age);
}
