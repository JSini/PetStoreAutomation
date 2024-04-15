package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DataDrivenTests {
	
	
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userID, String username, String fname, String lname, String useremail, String pwd, String phone) {
		
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setFirstName(username);
		userPayload.setLastName(fname);
		userPayload.setUsername(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		
		Response response = UserEndpoints.createUsers(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);	
		
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testGetUserByName(String username){

		Response response = UserEndpoints.readUsers(username);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);		
		
	}
	
	// Now that is have run testPostUser() test for all the data in the excel, Now lets delete the created users
	
	@Test(priority=3, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String username){
		
		Response response = UserEndpoints.deleteUsers(username);		
		Assert.assertEquals(response.getStatusCode(), 200);		
		
	}	

}
