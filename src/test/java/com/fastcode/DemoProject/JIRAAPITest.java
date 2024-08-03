package com.fastcode.DemoProject;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class JIRAAPITest {
	
	private String issueKey;
	
	@Test(priority = 1, enabled = true)
	public void createBug() throws Exception {
		
		RestAssured.baseURI = "https://fastcodeacademy.atlassian.net/";
		
		// Create bug
		String createIssue = given().header("Accept", "application/json").header("Content-Type", "application/json").header("Authorization", 
	    "Basic c2hhaG5hd2F6a2hhbjExOTgxQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBiWDk4cVZKeS1mUzBXMTJiQlVpckVBMWc4ZWNxVkpNTm1SMXlOb0pGVFNmQlVXOWtPQ0ttQTNnNkFQMFpVbEYyd2xWYWo2djRRWWJKTkROOWRSNnVYNnV4cUtNeHpTRUlQR0N1S0d4bklVb1hZVnRiMGNBT0FleEZrNU9uQ05nR3VpU3QyNE5Zd2dmZXJaRDZWTk40cFJSb0FPS0QyOTFKaEQwMl9UR05YWVE9NzI1NEE1RTk=").
		body("{\r\n"
				+ "  \"fields\": {\r\n"
				+ "     \"project\": {\r\n"
				+ "        \"key\": \"SCRUM\"\r\n"
				+ "     },\r\n"
				+ "     \"summary\": \"CrowdStrike faulty software update released issue\",\r\n"
				+ "     \"description\": \"The update, deployed on June 26, 2024, included changes to the Memory Scanning prevention policy, which caused the sensor to consume 100% of a single CPU core on affected Windows systems. This resulted in severe performance degradation and, in some cases, rendered systems inoperable until they could be manually rebooted​\",\r\n"
				+ "     \"issuetype\": {\r\n"
				+ "        \"name\": \"Bug\"\r\n"
				+ "     }\r\n"
				+ " }\r\n"
				+ "}").log().all().post("rest/api/2/issue").then().log().all().assertThat().statusCode(201).
		extract().response().asString();

		JsonPath js = new JsonPath(createIssue);
		String issueId = js.getString("id");
		issueKey= js.getString("key");
		System.out.println(issueId);
		System.out.println(issueKey);	
		
	}
	//Get bug
	@Test(priority = 2, enabled = false, dependsOnMethods = {"createBug"})
	public void getIssue() throws Exception {
		RestAssured.baseURI = "https://fastcodeacademy.atlassian.net/";
		
		// Create bug
		String getIssue = given().pathParam("key", issueKey).header("Content-Type", "application/json").header("Authorization", 
	    "Basic c2hhaG5hd2F6a2hhbjExOTgxQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBiWDk4cVZKeS1mUzBXMTJiQlVpckVBMWc4ZWNxVkpNTm1SMXlOb0pGVFNmQlVXOWtPQ0ttQTNnNkFQMFpVbEYyd2xWYWo2djRRWWJKTkROOWRSNnVYNnV4cUtNeHpTRUlQR0N1S0d4bklVb1hZVnRiMGNBT0FleEZrNU9uQ05nR3VpU3QyNE5Zd2dmZXJaRDZWTk40cFJSb0FPS0QyOTFKaEQwMl9UR05YWVE9NzI1NEE1RTk=").
		log().all().get("rest/api/2/issue/{key}").then().log().all().assertThat().statusCode(200).
		extract().response().asString();

		JsonPath js = new JsonPath(getIssue);
		System.out.println(js);
	}
	
	//Add Attachment
	@Test(priority = 3, enabled = true, dependsOnMethods = {"createBug"})
	public void addAttachment() throws Exception {
		RestAssured.baseURI = "https://fastcodeacademy.atlassian.net/";
			
		// Create bug
		String addAttachment = given().pathParam("key", issueKey).header("Accept", "application/json").header("X-Atlassian-Token","no-check").header("Authorization", 
		"Basic c2hhaG5hd2F6a2hhbjExOTgxQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBiWDk4cVZKeS1mUzBXMTJiQlVpckVBMWc4ZWNxVkpNTm1SMXlOb0pGVFNmQlVXOWtPQ0ttQTNnNkFQMFpVbEYyd2xWYWo2djRRWWJKTkROOWRSNnVYNnV4cUtNeHpTRUlQR0N1S0d4bklVb1hZVnRiMGNBT0FleEZrNU9uQ05nR3VpU3QyNE5Zd2dmZXJaRDZWTk40cFJSb0FPS0QyOTFKaEQwMl9UR05YWVE9NzI1NEE1RTk=").multiPart("file", new File("C:\\Users\\Shahnawaz Khan\\Rest-Assured\\DemoProject\\Files\\features-of-java.png")).
		log().all().post("rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200).
		extract().response().asString();

		JsonPath js = new JsonPath(addAttachment);
		System.out.println(js);
		}
}
