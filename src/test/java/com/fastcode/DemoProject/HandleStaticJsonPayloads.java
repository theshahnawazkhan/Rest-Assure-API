package com.fastcode.DemoProject;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HandleStaticJsonPayloads {

	@Test
	public void addPlace() throws IOException {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Shahnawaz Khan\\Rest-Assured\\DemoProject\\Files\\Payloads.json"))))
		.when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).extract()
		.response().asString();
		
		JsonPath js = new JsonPath(response);
		String palceId = js.get("place_id");
		System.out.println(palceId);
		
	}

}
