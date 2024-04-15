package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// Working with Properties files
//created to implement CRUD operations - Create, Read, Update, Delete

public class UserEndpoints2 {
	
	//method to get url from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //load properties file //name of the properties file, no need to give whole path, fetches from resources
		return routes;
	}
	
	
	
	
	public static Response createUsers(User payload) {
		
		String post_url = getURL().getString("post_url");
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		
		.when()
			.post(post_url);
		
		return response;
	}
	
	
	public static Response readUsers(String username) {
		
		String get_url = getURL().getString("get_url");
		
		Response response = given()
				.pathParam("username", username)
		
		.when()
			.get(get_url);
		
		return response;
	}
	
	public static Response updateUsers(String username, User payload) {
		
		String update_url = getURL().getString("update_url");
		
		
		Response response = given()
				.pathParam("username", username)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
		
		.when()
			.put(update_url);
		
		return response;
	}
	public static Response deleteUsers(String username) {
		
		String delete_url = getURL().getString("delete_url");
		
		
		Response response = given()
				.pathParam("username", username)

		
		.when()
			.delete(delete_url);
		
		return response;
	}


}
