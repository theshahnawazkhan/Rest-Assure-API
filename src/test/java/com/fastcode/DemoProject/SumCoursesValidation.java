package com.fastcode.DemoProject;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fast.usedFiles.PayLoads;

import io.restassured.path.json.JsonPath;

public class SumCoursesValidation {
	
	@Test
	public void sumOfCourses() {
		int sum = 0;
		JsonPath js = new JsonPath(PayLoads.coursePrice());
		
		int count = js.getInt("courses.size()");
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		
		for(int i = 0; i<count; i++) {
			
			int prices = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int amount = prices * copies;	
			System.out.println(amount);
			
			sum = sum + amount;
		}
		System.out.println(sum);
		Assert.assertEquals(sum, purchaseAmount);
		
	}

}
