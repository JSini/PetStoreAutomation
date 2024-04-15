package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//created to implement CRUD operations - Create, Read, Update, Delete

public class UserEndpoints {
	
	
	public static Response createUsers(User payload) {
		
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		
		.when()
			.post(Routes.post_url);
		
		return response;
	}
	
	
	public static Response readUsers(String username) {
		
		
		Response response = given()
				.pathParam("username", username)
		
		.when()
			.get(Routes.get_url);
		
		return response;
	}
	
	public static Response updateUsers(String username, User payload) {
		
		
		Response response = given()
				.pathParam("username", username)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
		
		.when()
			.put(Routes.update_url);
		
		return response;
	}
	public static Response deleteUsers(String username) {
		
		
		Response response = given()
				.pathParam("username", username)

		
		.when()
			.delete(Routes.delete_url);
		
		return response;
	}


}
