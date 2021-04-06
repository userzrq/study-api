package com.userzrq.standard.controller;

import com.userzrq.standard.annotation.ResponseResult;
import com.userzrq.standard.entity.Order;
import com.userzrq.standard.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 10017
 */
@RestController
@RequestMapping("/orders")
@ResponseResult
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("{id}")
    public Order getOrder(@PathVariable("id") Integer id) {

        Order order = orderService.getOrderById(id);
        return order;
    }
}
