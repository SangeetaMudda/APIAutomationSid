package utils;


import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;


public class JsonReader {
    public static String getTestData(String key)throws IOException, ParseException {
        String testData;
        return testData = (String) getJsonData().get(key);

    }

    public static JSONObject getJsonData() throws IOException, ParseException {
        //pass the path of the test data josn file
        File filename = new File("resources//TestData//testData.json");
        //convert JSON file into String
        String json = FileUtils.readFileToString(filename, "UTF-8");
        //prase this string into object
       Object obj = new JSONParser().parse(json);
       //give json object so that I can return it to the function everytime it get called
        JSONObject jsonObj = (JSONObject) obj;
        return jsonObj;

    }

    public static JSONArray getJsonArray(String key) throws ParseException, IOException {
        JSONObject jsonObject = getJsonData();
        JSONArray jsonArray = (JSONArray) jsonObject.get(key);
        return jsonArray;
    }

    public static Object getJsonArrayData(String key, int index) throws ParseException, IOException {{
        JSONArray languages = getJsonArray("languages");
        return languages.get(index);
    }

    }


}
