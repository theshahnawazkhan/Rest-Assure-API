package com.fast.usedFiles;

import io.restassured.path.json.*;
import io.restassured.response.Response;

public class ReusableMethods {

    public static JsonPath rawToJson(String res) {
        return new JsonPath(res);
    }
}