package com.lumos.assignment.controller;

import com.lumos.assignment.model.UserEntity;
import com.lumos.assignment.service.OrderService;
import com.lumos.assignment.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    // Registration page
    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               Model model) {
        if(userService.isUsernameTaken(username)) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userService.saveUser(user);
        model.addAttribute("success", "Registration successful! Please login.");
        return "login";
    }

    // Login page
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        UserEntity user = userService.authenticate(username, password);
        if(user == null) {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
        session.setAttribute("user", user);
        return "redirect:/order";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // Order page
    @GetMapping("/order")
    public String showOrder(HttpSession session, Model model) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        model.addAttribute("orders", orderService.getOrdersByUser(user));
        return "order";
    }

    @PostMapping("/order")
    public String placeOrder(@RequestParam String dishName,
                             @RequestParam int quantity,
                             HttpSession session,
                             Model model) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        orderService.saveOrder(dishName, quantity, user);
        model.addAttribute("orders", orderService.getOrdersByUser(user));
        model.addAttribute("message", "Order placed successfully!");
        return "order";
    }
}
