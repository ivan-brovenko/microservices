package com.istore.dbtransferservice.resources;

import org.json.JSONArray;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/transfer")
public class TransferResource {

    @PostMapping("/mongo")
    public void transferMySQLToMongo() {
        transfer("http://localhost:8081/istore/roles/all", "http://localhost:8083/istore/roles/add");
        transfer("http://localhost:8081/istore/usesr/all", "http://localhost:8083/istore/users/add");
        transfer("http://localhost:8081/istore/orders/all", "http://localhost:8083/istore/orders/add");
        transfer("http://localhost:8081/istore/categories/all", "http://localhost:8083/istore/categories/add");
        transfer("http://localhost:8081/istore/products/all", "http://localhost:8083/istore/products/add");
    }

    @PostMapping("/mysql")
    public void transferMongoToMysql() {
        transfer("http://localhost:8083/istore/roles/all", "http://localhost:8081/istore/roles/add");
        transfer("http://localhost:8083/istore/usesr/all", "http://localhost:8081/istore/users/add");
        transfer("http://localhost:8083/istore/orders/all", "http://localhost:8081/istore/orders/add");
        transfer("http://localhost:8083/istore/categories/all", "http://localhost:8081/istore/categories/add");
        transfer("http://localhost:8083/istore/products/all", "http://localhost:8081/istore/products/add");
    }

    private void transfer(String from, String to) {
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(from, String.class);
        JSONArray jsonArray = new JSONArray(response);
        jsonArray.forEach(role -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            restTemplate.exchange(to, HttpMethod.POST, new HttpEntity<>(role.toString(), headers), new ParameterizedTypeReference<String>() {
            });
        });
    }
}
