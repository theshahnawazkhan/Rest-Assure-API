package com.fastcode.DemoProject;

import org.testng.Assert;

import com.fast.usedFiles.PayLoads;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(PayLoads.coursePrice());
		
		//Print number of courses returned by api
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//print purchase amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		//print title of the first cpourse
		String firstTitle = js.getString("courses[0].title");
		System.out.println(firstTitle);
		
		// Print All course titles and their respective Prices
		
		for(int i = 0; i<count; i++) {
			
			System.out.println(js.get("courses["+i+"].title"));
			System.out.println(js.get("courses["+i+"].price"));
		}
		
		// Print no of copies sold by RPA Course
		
		for(int i = 0; i<count; i++) {
			
			String courseTitles = js.getString("courses["+i+"].title");
			
			if(courseTitles.equalsIgnoreCase("RPA")) {
				
				int copies = js.get("courses["+i+"].copies");
				System.out.println("copies = "+copies);
			}		
		}		
	}
}
