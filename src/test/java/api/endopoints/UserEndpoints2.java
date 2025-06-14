package api.endopoints;

import static io.restassured.RestAssured.*; // For given(), when(), then(), etc.
import static io.restassured.matcher.RestAssuredMatchers.*; // For advanced matchers
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Created to perform Create, Retrieve, Update and Delete request to the user API.
public class UserEndpoints2 {

	//method created for getting URL's from properties file
	static ResourceBundle getURL(){
		
		ResourceBundle route=ResourceBundle.getBundle("routes"); //Load the properties file //Inside name of the properties file
		return route; //It will return all the routes.
	}
	
	public static Response CreateUser(User payload) {

		String Post_Url=getURL().getString("post_url");
		Response res = given()

				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

				.when()
				.post(Post_Url);

		return res;

	}

	public static Response GetUser(String userName) {
		String Get_Url=getURL().getString("get_url");
		Response res = given()

				.pathParam("username", userName)

				.when()
				.get(Get_Url);

		return res;

	}
	
	public static Response UpdateUser(String userName,User payload) {
		String Update_Url=getURL().getString("update_url");
		Response res = given()

				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", userName)
				.when()
				.put(Update_Url);

		return res;

	}
	public static Response DeleteUser(String userName) {
		String Delete_Url=getURL().getString("delete_url");
		Response res = given()

				.pathParam("username", userName)

				.when()
				.delete(Delete_Url);

		return res;

	}
}
