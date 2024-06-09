package com.fastcode.DemoProject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fast.usedFiles.PayLoads;
import com.fast.usedFiles.ReusableMethods;

public class LibraryDynamicJson {

	private static ArrayList<String> bookIds = new ArrayList<>();

	@Test(dataProvider = "BooksData")
	public static void addBook(String isbn, String aisle) {

		RestAssured.baseURI = "http://216.10.245.166";

		String res = given().log().all().header("Content-Type", "application/json")
				.body(PayLoads.addLibraryBook(isbn, aisle)).when().post("Library/Addbook.php").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();
		JsonPath js = ReusableMethods.rawToJson(res);
		String bookId = js.getString("ID");
		bookIds.add(bookId);
		System.out.println(bookIds);
		
	}
	
    @Test(dependsOnMethods = "addBook")
    public void deleteBooks() {
        for (String bookId : bookIds) {
            String deletePayload = "{ \"ID\" : \"" + bookId + "\" }";
            given().log().all().header("Content-Type", "application/json")
                    .body(deletePayload)
                    .when().post("/Library/DeleteBook.php")
                    .then().log().all().assertThat().statusCode(200);
        }
        bookIds.clear(); // Clear the list after deletion
    }
	@DataProvider(name = "BooksData")
	public Object[][] getData() {

		return new Object[][] { { "dchjj", "23456" }, { "dfqglk", "345668" }, { "vcfsqpo", "2215" } };
	}

}
