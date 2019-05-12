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
        transfer("http://localhost:8081/istore/roles/all", "http://localhost:8083/istore/roles/addAll");
        transfer("http://localhost:8081/istore/users/all", "http://localhost:8083/istore/users/addAll");
        transfer("http://localhost:8081/istore/orders/all", "http://localhost:8083/istore/orders/addAll");
        transfer("http://localhost:8081/istore/categories/all", "http://localhost:8083/istore/categories/addAll");
        transfer("http://localhost:8081/istore/products/all", "http://localhost:8083/istore/products/addAll");
    }

    @PostMapping("/mysql")
    public void transferMongoToMysql() {
        transfer("http://localhost:8083/istore/roles/all", "http://localhost:8081/istore/roles/addAll");
        transfer("http://localhost:8083/istore/users/all", "http://localhost:8081/istore/users/addAll");
        transfer("http://localhost:8083/istore/orders/all", "http://localhost:8081/istore/orders/addAll");
        transfer("http://localhost:8083/istore/categories/all", "http://localhost:8081/istore/categories/addAll");
        transfer("http://localhost:8083/istore/products/all", "http://localhost:8081/istore/products/addAll");
    }

    private void transfer(String from, String to) {
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(from, String.class);
        System.out.println(response);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.exchange(to, HttpMethod.POST, new HttpEntity<>(response, headers), new ParameterizedTypeReference<String>() {
        });
    }
}
