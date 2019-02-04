package com.istore.mysqldbservice.resource;

import com.istore.mysqldbservice.model.Order;
import com.istore.mysqldbservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/istore/orders")
public class OrderResource {

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }
}
