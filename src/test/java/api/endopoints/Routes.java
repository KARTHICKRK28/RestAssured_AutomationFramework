package api.endopoints;

/*Swagger URI --> https://petstore.swagger.io

Create user (Post) : https://petstore.swagger.io/v2/user
Get user (Get) : https://petstore.swagger.io/v2/user/{username}
Update user (Put) : https://petstore.swagger.io/v2/user/{username}
Delete user (Delete) : https://petstore.swagger.io/v2/user/{username} */

public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//User Module
	//It's like chaining process, so we don't know which username it will be stored
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	
	//Pet Module
	
	//Store Module

}
