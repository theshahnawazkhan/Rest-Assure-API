package com.fastcode.DemoProject;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;

public class AuthorizationServer {
	
	public static void main(String[] args) {
		
		//Post
		
		String postResponse = given().formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParams("grant_type","client_credentials")
		.formParams("scope","trust").when().log().all()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		
		System.out.println(postResponse);
		
		JsonPath js = new JsonPath(postResponse);
		String accessToken = js.getString("access_token");
		System.out.println(accessToken);
		
		//Get
		String getResponse = given().queryParam("access_token", accessToken)
		.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
		
		System.out.println(getResponse);
		
		
	}

}
