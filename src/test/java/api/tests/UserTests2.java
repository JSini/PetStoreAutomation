package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
	
	Faker faker;
	User userPayload;
	public Logger logger;	
	
	@BeforeClass
	public void setUpData() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setUsername(faker.name().username());
		userPayload.setPassword(faker.internet().password());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		
		
	}
	@Test(priority=1)
	public void testPostUser(){
		
		logger.info("************* Creating User *************");
		Response response = UserEndpoints2.createUsers(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);		
		
	}
	
	@Test(priority=2)
	public void testGetUserByName(){
		
		logger.info("************* Retrieving User Info *************");
		Response response = UserEndpoints2.readUsers(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);		
		
	}
	
	@Test(priority=3)
	public void testUpdateUserByName(){
		
		logger.info("************* Updating User *************");
		logger.debug("Update");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndpoints2.updateUsers(this.userPayload.getUsername(), userPayload);
		response.then().log().body().statusCode(200);
		
		Assert.assertEquals(response.getStatusCode(), 200);	
		
		logger.info("************* User Updated *************");
		Response responseAfterUpdate = UserEndpoints2.readUsers(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	
	}
	
	@Test(priority=4)
	public void testDeleteUser(){
		
		logger.info("************* Deleting User *************");
		
		Response response = UserEndpoints2.deleteUsers(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);		
		
		logger.info("************* User Deleted Successfully *************");
	}
	
}
