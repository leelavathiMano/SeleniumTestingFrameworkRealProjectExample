package com.leelavathienterprise.Tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class dataReader {
public List<HashMap<String, String>> getJsonDataIntoMap() throws IOException{
    //readJson to string
   String jsonContent= FileUtils.readFileToString(new File(System.getProperty("user.dir") + "/src/test/java/com/leelavathienterprise/dataSet/purchaseOrder.json"),
    StandardCharsets.UTF_8);
    //convert string to hashmap
    //jackson databind is dependency which helps to convert string to hashmap
    ObjectMapper objectMapper = new ObjectMapper();
    List<HashMap<String, String>> data = objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
    return data;
}
}
