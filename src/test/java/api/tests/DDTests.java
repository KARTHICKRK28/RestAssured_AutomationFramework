package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endopoints.UserEndpoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	@Test(priority = 1,dataProvider="Data",dataProviderClass=DataProviders.class)
	void testPostuser(String userID,String userName,String fName,String lName,String useremail,String pwd,String ph) {
		
		User userpayload= new User();
		userpayload.setId(Integer.parseInt(userID));
		userpayload.setUsername(userName);
		userpayload.setFirstName(fName);
		userpayload.setLastName(lName);
		userpayload.setEmail(useremail);
		userpayload.setPassword(pwd);
		userpayload.setPhone(ph);
		
		Response res=UserEndpoints.CreateUser(userpayload);
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(dependsOnMethods = "testPostuser",dataProvider="UserNames",dataProviderClass=DataProviders.class)
	void testGetuserbyUserName(String userName) {
		
		Response res=UserEndpoints.GetUser(userName);
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(dependsOnMethods = "testGetuserbyUserName",dataProvider="UserNames",dataProviderClass=DataProviders.class)
	void testDeleteuserbyUserName(String userName) {
		
		Response res=UserEndpoints.DeleteUser(userName);
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
