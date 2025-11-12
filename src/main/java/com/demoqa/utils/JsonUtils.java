//package com.demoqa.utils;
//
//import com.google.gson.*;
//import java.io.FileReader;
//import java.util.*;
//
//public class JsonUtils {
//
//    public static Object[][] getJsonData(String filePath) {
//        try (FileReader reader = new FileReader(filePath)) {
//            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
//
//            Object[][] data = new Object[jsonArray.size()][2];
//            for (int i = 0; i < jsonArray.size(); i++) {
//                JsonObject obj = jsonArray.get(i).getAsJsonObject();
//                data[i][0] = obj.get("username").getAsString();
//                data[i][1] = obj.get("password").getAsString();
//            }
//
//            return data;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new Object[0][0];
//        }
//    }
//}
//
//

package com.demoqa.utils;

import com.google.gson.*;
import java.io.FileReader;
import java.util.*;

public class JsonUtils {

    public static Object[][] getJsonData(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            // ✅ Works for Gson 2.8.6+ (parseReader is static)
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

            Object[][] data = new Object[jsonArray.size()][2];
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.get(i).getAsJsonObject();
                data[i][0] = obj.get("username").getAsString();
                data[i][1] = obj.get("password").getAsString();
            }

            return data;
        } catch (Exception e) {
            System.out.println("⚠️ Error reading JSON: " + e.getMessage());
            e.printStackTrace();
            return new Object[0][0];
        }
    }
}
