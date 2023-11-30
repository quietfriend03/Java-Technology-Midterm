package com.example.midterm.controller;

import com.example.midterm.model.Cart;
import com.example.midterm.model.CartItem;
import com.example.midterm.service.CartServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartServiceImpl cartService;

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        Cart cart = cartService.getCart(session);
        List<CartItem> cartItems = cart.getCartItems();
        double totalPrice = calculateTotalPrice(cartItems);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("productId") Long productId,
                            @RequestParam("quantity") int quantity,
                            HttpSession session){
        cartService.addToCart(session,productId, quantity);
        return "redirect:/cart";
    }

    private double calculateTotalPrice(List<CartItem> cartItems) {
        double totalPrice = 0.0;

        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }

        return totalPrice;
    }
}
