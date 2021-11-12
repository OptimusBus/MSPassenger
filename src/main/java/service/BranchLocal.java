package service;

import java.util.List;

import javax.ejb.Local;

import org.bson.Document;

import model.Passenger;

@Local
public interface BranchLocal {
	/**
	 * Create a new Passenger on DB
	 * @param name the name of the passenger
	 * @param surname the surname of the passenger
	 * @param i the age of the passenger (int)
	 * @param email the email of the passenger
	 * @return the passengerId
	 */
	public Passenger createPassenger(Document d);
	
	/**
	 * Get all the passengers stored on DB
	 * @return the List of the passengers
	 */
	public List<Passenger> getAllPassenger();
	
	/**
	 * Get a passenger on database by its passengerId
	 * @param passengerId of the passenger to get from the database
	 * @return the Passenger or null if the passenger is not found
	 */
	public Passenger getPassengerById(String passengerId);
	
	/**
	 * Get the passenger on the DB by its email address
	 * @param email of the passenger to get from the database
	 * @return the Passenger or null if the passenger is not found
	 */
	public Passenger getPassengerByEmail(String email);
	
	/**
	 * Get all passengers from the DB for given name
	 * @param name of the passengers to get from the database
	 * @return the List<Passenger> found or null if there aren't no passenger with that name
	 */
	public List<Passenger> getPassengerByName(String name);
	
	/**
	 * Get all passengers from the DB for given surname
	 * @param surname of the passengers to get from the database
	 * @return the List<Passenger> found or null if there aren't no passenger with that surname
	 */
	public List<Passenger> getPassengerBySurname(String surname);
	
	/**
	 * Remove a passenger from the database
	 * @param passengerId of the passenger to be removed
	 * @return the result of the operation (true if succeded or false if failed)
	 */
	public boolean removePassenger(String passengerId);
	
	/**
	 * Get all passengers from the DB for given age
	 * @param age of the passengers to get from the database
	 * @return the List<Passenger> found or null if there aren't no passenger with that age
	 */
	public List<Passenger> getPassengerByAge(String age);
	
	/**
	 * Get all passengers from the DB for age greater then the parameter age
	 * @param age of the passengers to get from the database
	 * @return the List<Passenger> found or null if there aren't no passenger with age greater than the parameter 
	 */
	public List<Passenger> getPassengerByAgeGT(String age);
	
	/**
	 * Get all passengers from the DB for age less then the parameter age
	 * @param age of the passengers to get from the database
	 * @return the List<Passenger> found or null if there aren't no passenger with age less than the parameter 
	 */
	public List<Passenger> getPassengerByAgeLT(String age);
	/**
	 * Get passenger by username
	 * @param username of the passenger
	 * @return The passenger for given username
	 */
	public Passenger getPassengerByUsername(String username);
}
