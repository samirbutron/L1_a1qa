package src.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class SettingsReader {
    //;
    private final ObjectMapper objectMapper;
    private ObjectNode objectNode;

    public SettingsReader(Path filepath) throws IOException {
        objectMapper = new ObjectMapper();
        String contents = new String(Files.readAllBytes(filepath));
        objectNode = (ObjectNode) objectMapper.readTree(contents);
    }

    public SettingsReader(String filepath) throws IOException{
        this(Path.of(filepath));
    }

    public void readFile(String filepath) throws IOException {
        byte[] fileBytes = Files.readAllBytes(Path.of(filepath));
        JsonNode jsonNode = objectMapper.readTree(fileBytes);
        if (jsonNode instanceof ObjectNode) {
            objectNode = (ObjectNode) jsonNode;
        } else {
            throw new IllegalArgumentException("Config file must contain a JSON object");
        }
    }
    public Object getValue(String key) {
        JsonNode node = objectNode.get(key);
        if (node.isValueNode()) {
            return node;
        } else {
            throw new IllegalArgumentException(key + " was not found");
        }
    }


    public String getString(String key) {
        return objectNode.get(key).asText();
    }
    public int getInt(String key) {
        return objectNode.get(key).asInt();
    }


    public List<Object> getList(String key) {
        JsonNode node = objectNode.get(key);
        if (node.isArray()) {
            List<Object> list = new ArrayList<>();
            for (JsonNode element : node) {
                if (element.isValueNode()) {
                    list.add(element.asText());
                } else if (element.isObject()) {
                    list.add(objectMapper.convertValue(element, Map.class));
                } else {
                    throw new IllegalArgumentException(key + " contains an invalid element");
                }
            }
            return list;
        } else {
            throw new IllegalArgumentException(key + " is not an array");
        }
    }

    public Map<String, Object> getMap(String key) {
        JsonNode node = objectNode.get(key);
        if (node.isObject()) {
            Map<String, Object> map = new HashMap<>();
            for (Iterator<String> it = node.fieldNames(); it.hasNext(); ) {
                String fieldName = it.next();
                JsonNode valueNode = node.get(fieldName);
                if (valueNode.isValueNode()) {
                    map.put(fieldName, valueNode.asText());
                } else if (valueNode.isObject()) {
                    map.put(fieldName, objectMapper.convertValue(valueNode, Map.class));
                } else {
                    throw new IllegalArgumentException(key + " contains an invalid value");
                }
            }
            return map;
        } else {
            throw new IllegalArgumentException(key + " is not an object");
        }
    }
}

