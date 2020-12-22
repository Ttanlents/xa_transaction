package com.lscl.controller;


import com.lscl.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/addOrders/{flag}")
    public String add(@PathVariable Integer flag) throws Exception{
        ordersService.add(flag);;
        return "ok";
    }
}
