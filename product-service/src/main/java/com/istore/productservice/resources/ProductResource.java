package com.istore.productservice.resources;

import com.istore.productservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/istore/product/orders")
public class ProductResource {
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createOrder() {
        return restTemplate.getForObject("http://localhost:8081/istore/users/all", String.class);
    }
}
