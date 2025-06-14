package api.endopoints;

import static io.restassured.RestAssured.*; // For given(), when(), then(), etc.
import static io.restassured.matcher.RestAssuredMatchers.*; // For advanced matchers
import static org.hamcrest.Matchers.*;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Created to perform Create, Retrieve, Update and Delete request to the user API.
public class UserEndpoints {

	public static Response CreateUser(User payload) {

		Response res = given()

				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

				.when()
				.post(Routes.post_url);

		return res;

	}

	public static Response GetUser(String userName) {

		Response res = given()

				.pathParam("username", userName)

				.when()
				.get(Routes.get_url);

		return res;

	}
	
	public static Response UpdateUser(String userName,User payload) {

		Response res = given()

				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", userName)
				.when()
				.put(Routes.update_url);

		return res;

	}
	public static Response DeleteUser(String userName) {

		Response res = given()

				.pathParam("username", userName)

				.when()
				.delete(Routes.delete_url);

		return res;

	}
}
