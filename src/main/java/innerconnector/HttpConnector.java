package innerconnector;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

public class HttpConnector {
	
	/**
	 * Generic request maker for Http request
	 * @param baseAddress the address of the service
	 * @param url the url of the request
	 * @param m the type of request to execute (GET, POST, DELETE, PUT)
	 * @param queryParam a map of query param - value for multiple param GET request
	 * @param params the params for POST and PUT request
	 * @return a Response or null
	 */
	private static Response makeRequest(String baseAddress, String url, Method m, Map<String,String> queryParam, String params) {	
		WebClient client = WebClient.create(baseAddress);
		client.accept("application/json");
		client.type("application/json");
		client.path(url);
		switch(m) {
			case GET:
				if(queryParam != null) {
					for (Entry<String, String> entry : queryParam.entrySet()) {
				        client.query(entry.getKey(), entry.getValue());
				    }
				}
				return client.get();
			case POST:
				if(params != null) return client.post(params);
			case PUT:
				if(params != null) return client.put(params);
			case DELETE:
				if(params != null) return client.delete();
			default:
				return null;
		}
	}
	
	/**
	 * Request the passengerReg for the given passengerId from MSSecurity service
	 * @param passengerId of the passengerReg
	 * @return a Response
	 */
	public static Response getPassengerReg(String passengerId) {
		return makeRequest(securityAddr, "/"+passengerId, Method.GET, null, null);
	}
	/**
	 * Request the creation of a PassengerReg from JSON data to the MSSecurity service
	 * @param request JSON data of the passengerReg to be registered
	 * @return a Response containing the result of the operation
	 */
	public static Response createPassengerReg(String request) {
		return makeRequest(securityAddr, "", Method.POST, null, request);
	}
	
	private static final String securityAddr="";
	public static enum Method {GET, POST, PUT, DELETE}
}
