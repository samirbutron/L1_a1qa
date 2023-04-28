package src.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class SettingsReader {
    private final ObjectMapper objectMapper;
    private ObjectNode objectNode;

    public SettingsReader(Path filepath) {
        objectMapper = new ObjectMapper();
        String contents;
        try {
            contents = new String(Files.readAllBytes(filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            objectNode = (ObjectNode) objectMapper.readTree(contents);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public SettingsReader(String filepath) {
        this(Path.of(filepath));
    }

    public void readFile(String filepath) {
        byte[] fileBytes = new byte[0];
        try {
            fileBytes = Files.readAllBytes(Path.of(filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(fileBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (jsonNode instanceof ObjectNode) {
            objectNode = (ObjectNode) jsonNode;
        } else {
            throw new IllegalArgumentException("Config file must contain a JSON object");
        }
    }

    public Object getValue(String key) {return objectNode.get(key);}
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

