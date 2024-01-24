package com.laptrinhweb.utils;

import java.util.HashMap;
import java.util.Map;

public class BuildingTypeUtils {
    public static Map<String , String> getTypes(){
        Map<String, String> results = new HashMap<>();
        results.put("tang_tret", "tang tret");
        results.put("nguyen_can", "nguyen can");
        results.put("noi_that", "noi that");
        return results;
    }
}
