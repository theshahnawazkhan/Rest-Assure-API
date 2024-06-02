package com.fastcode.DemoProject;

import io.restassured.path.json.*;

public class ReusableMethods {

    public static JsonPath rawToJson(String response) {
        return new JsonPath(response);
    }
}