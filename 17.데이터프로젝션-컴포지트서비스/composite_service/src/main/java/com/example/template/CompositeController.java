package com.example.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class CompositeController {

    @Autowired
    CompositeService compositeService;

    @GetMapping("/composite/orders/{customerId}")
    List<OrderInfo> getOrderByCustomerId(@PathVariable(value = "customerId") String customerId) throws ExecutionException, InterruptedException {
        return  this.compositeService.getOrderByCustomerId(customerId);
    }

}
