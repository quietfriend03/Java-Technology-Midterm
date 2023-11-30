package com.example.midterm.service;

import com.example.midterm.model.Cart;
import com.example.midterm.model.CartItem;
import com.example.midterm.model.Product;
import com.example.midterm.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl {
    @Autowired
    private ProductService productService;
    public void addToCart(HttpSession session, Long productId, int quantity) {
        // Get the user's cart from the session
        Cart cart = (Cart) session.getAttribute("cart");

        // If the cart doesn't exist in the session, create a new one
        if (cart == null) {
            cart = new Cart();
        }

        // Get product details from ProductService
        Product product = productService.getProductById(productId);

        // Create a new CartItem and add it to the cart
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        cart.getCartItems().add(cartItem);

        // Update the cart in the session
        session.setAttribute("cart", cart);
    }

    public Cart getCart(HttpSession session) {
        // Get the user's cart from the session
        Cart cart = (Cart) session.getAttribute("cart");

        // If the cart doesn't exist in the session, create a new one
        if (cart == null) {
            cart = new Cart();
        }

        return cart;
    }
}
