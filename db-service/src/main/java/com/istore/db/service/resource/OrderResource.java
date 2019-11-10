package com.istore.db.service.resource;

import com.istore.db.service.model.Order;
import com.istore.db.service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/istore/orders")
public class OrderResource {
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @RequestMapping(value = "/addAll", method = RequestMethod.POST)
    public List<Order> addAllOrders(@RequestBody List<Order> orders) {
        return orderRepository.saveAll(orders);
    }
}
