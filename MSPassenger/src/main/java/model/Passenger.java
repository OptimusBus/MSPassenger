package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Passenger {
	private String passengerId;
	private String name;
	private String surname;
	private int age;
	private String email;
	
	public Passenger(String passengerId, String name, String surname, int age, String email) {
		this.passengerId = passengerId;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
	}
	
	public Passenger(){}

	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
