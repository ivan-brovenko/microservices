package com.istore.dbtransferservice.resources;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferResource {

    @PostMapping("/roles")
    public List<String> transferUsersFromMySQLToMongo() {
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject("http://localhost:8081/istore/roles/all", String.class);
        JSONArray jsonArray = new JSONArray(response);
        jsonArray.forEach(role -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            restTemplate.exchange("http://localhost:8083/istore/roles/add", HttpMethod.POST, new HttpEntity<>(role.toString(), headers), new ParameterizedTypeReference<String>() {
            });
        });
        return Collections.emptyList();
    }
}
