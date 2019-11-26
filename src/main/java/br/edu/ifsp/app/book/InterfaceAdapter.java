package br.edu.ifsp.app.book;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.protobuf.Type;

public class InterfaceAdapter implements JsonSerializer, JsonDeserializer{

    private static final String CLASSNAME = "CLASSNAME";
    private static final String DATA = "DATA";

    public <T> T deserialize(JsonElement jsonElement, Type type,
        JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = prim.getAsString();
        Class klass = getObjectClass(className);
            return jsonDeserializationContext.deserialize(jsonObject.get(DATA), klass);
        }
    
    
    
     public <T> JsonElement serialize(T jsonElement, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(CLASSNAME, jsonElement.getClass().getName());
            jsonObject.add(DATA, jsonSerializationContext.serialize(jsonElement));
            return jsonObject;
        }
    /****** Helper method to get the className of the object to be deserialized *****/
        public Class getObjectClass(String className) {
            try {
                return Class.forName(className);
                } catch (ClassNotFoundException e) {
                    //e.printStackTrace();
                    throw new JsonParseException(e.getMessage());
                }
        }
        
	@Override
	public Object deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) {
    JsonObject jsonObject = json.getAsJsonObject();
    JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
    String className = prim.getAsString();
    Class klass = getObjectClass(className);
        return context.deserialize(jsonObject.get(DATA), klass);
	}

	@Override
	public JsonElement serialize(Object src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(CLASSNAME, src.getClass().getName());
        jsonObject.add(DATA, context.serialize(src));
        return jsonObject;
	}

    }