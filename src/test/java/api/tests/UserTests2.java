package api.tests;

import org.apache.logging.log4j.LogManager;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endopoints.UserEndpoints;
import api.endopoints.UserEndpoints2;
import api.payloads.User;
import io.restassured.response.Response;


public class UserTests2 {

	Faker faker;
	User userpayload;
	public Logger logger; // for logs-- created 1 variable
	@BeforeClass
	void Setup() {
		
		//Creating the test data to POJO
		faker= new Faker();
		userpayload= new User();
		
		userpayload.setId(faker.idNumber().hashCode()); //to generate random id number
		userpayload.setUsername(faker.name().username());
        userpayload.setFirstName(faker.name().firstName());
        userpayload.setLastName(faker.name().lastName());
        userpayload.setEmail(faker.internet().safeEmailAddress());
        userpayload.setPassword(faker.internet().password(5, 10)); // Generate a password with length between 5 and 10
        userpayload.setPhone(faker.phoneNumber().cellPhone());
              
       	//Logs Initiation
        //Using this variable , initiated the logger class
        logger= LogManager.getLogger(this.getClass());
	}
	@Test(priority = 1)
	void test_PostUser() {
		
		//using the same data we are posting it here
		logger.info("********** Creating the User ***********");
		Response res=UserEndpoints2.CreateUser(userpayload);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("********** User is created ***********");
		
		logger.debug("*********** Debugging the code ***********");
		

		
		
	}
	
	@Test(priority = 2)
	void test_GetUserbyName() {
		
		logger.info("********** Reading the User ***********");
		
		//using the same data we are posting it here
		Response res=UserEndpoints2.GetUser(this.userpayload.getUsername());
		res.then().log().body();		
		Assert.assertEquals(res.getStatusCode(), 200);
		res.then()
		   .assertThat()
		   .body(matchesJsonSchemaInClasspath("schema/userSchema.json"));

		logger.info("********** User Info is readed successfully ***********");
		
		
			}
	
	@Test(priority = 3)
	void test_UpdateUserbyName() {
		logger.info("********** Updating the User ***********");
		
		//Re-generating the new data for the below fields
		userpayload.setFirstName(faker.name().firstName());
        userpayload.setLastName(faker.name().lastName());
        userpayload.setEmail(faker.internet().safeEmailAddress());
      //updating the data and we are posting it here
		Response res=UserEndpoints2.UpdateUser(this.userpayload.getUsername(),userpayload);
		res.then().log().body();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("********** User is updated successfully ***********");
		
		
		//Checking the data after updating
		logger.info("********** Reading the Updated User ***********");
		
		Response res_afterupdate=UserEndpoints2.GetUser(this.userpayload.getUsername());
		
		
		Assert.assertEquals(res_afterupdate.getStatusCode(), 200);
		

		
	}
	@Test(priority = 4)
	void test_DeleteUserbyName() {
		logger.info("********** Deleting the User ***********");
		//using the same data we are posting it here
		Response res=UserEndpoints2.DeleteUser(this.userpayload.getUsername());
		
		logger.info("********** Deleted the User Successfully ***********");
		Assert.assertEquals(res.getStatusCode(), 200);
		

		
			}
	
	
}
