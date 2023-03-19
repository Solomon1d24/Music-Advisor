package advisor.helper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonHelper {

    /**
     * This method is to parse the String object into a json element which then can be used by different instance of different classes
     * @param json : json like object in json format
     * @param field : the field that target to be extracted from the json object to form the JsonElement
     * @return  JsonElement
     * */
    public static JsonElement getField(String json, String field) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return jsonObject.get(field);
    }
}
