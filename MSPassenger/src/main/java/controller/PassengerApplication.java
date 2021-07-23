package controller;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/Passenger-1.0")
public class PassengerApplication extends Application{
	public Set<Class<?>> getClasses(){
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(controller.PassengerController.class);
		return s;
	}
	
	public Set<Object> getSingletons(){
		Set<Object> s = new HashSet<Object>();
		s.add(new controller.PassengerController());
		return s;
	}
}
