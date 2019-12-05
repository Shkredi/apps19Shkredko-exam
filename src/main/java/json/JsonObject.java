package json;

import java.util.Hashtable;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private Hashtable<String, Json> dict;

    public JsonObject(JsonPair... jsonPairs) {
        this.dict = new Hashtable<>();
        for (JsonPair jPair: jsonPairs) {
            this.add(jPair);
        }
    }

    @Override
    public String toJson() {

        StringBuilder json = new StringBuilder("{");
        for (String name: this.dict.keySet()) {
            json.append("'");
            json.append(name);
            json.append("': ");
            json.append(this.dict.get(name).toJson());
            json.append(",");
        }
        int l = json.length();
        if (json.charAt(l - 1) == ','){
            json.delete(l - 1, l);
        }
        json.append("}");
        return json.toString();
    }

    public void add(JsonPair jsonPair) {
        this.dict.put(jsonPair.key, jsonPair.value);
    }

    public boolean contains(String name){
        return this.dict.contains(name);
    }

    public Json find(String name) {
        if (this.dict.containsKey(name)){
            return this.dict.get(name);
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject jObj = new JsonObject();
        for (String name: names) {
            Json j = this.find(name);
            if (j != null){
                jObj.add(new JsonPair(name, j));
            }
        }
        return jObj;
    }
}
