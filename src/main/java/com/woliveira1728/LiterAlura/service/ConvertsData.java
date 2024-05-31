package com.woliveira1728.LiterAlura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConvertsData {

    private ObjectMapper mapper = new ObjectMapper();

    public <T> T getData(String json, Class<T> classValue) {
        try {
            return mapper.readValue(json, classValue);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> getList(String json, Class<T> classValue) {
        CollectionType list = mapper.getTypeFactory()
                .constructCollectionType(List.class, classValue);

        try {
            return mapper.readValue(json, list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String extractObjectJson(String json, String object) {
        try {
            JsonNode integerJson = mapper.readTree(json);
            JsonNode bookJson = integerJson.path("results");
            return bookJson.toString();
        } catch(JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
