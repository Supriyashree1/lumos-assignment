package com.lumos.assignment.controller;



import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {

    @PostMapping("/order")
    public String placeOrder(@RequestBody String order) {
        // Simple API response, no DB persistence
        return "{\"status\":\"order placed\"}";
    }
}
