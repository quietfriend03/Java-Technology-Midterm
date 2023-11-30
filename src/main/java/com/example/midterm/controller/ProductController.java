package com.example.midterm.controller;

import com.example.midterm.model.Product;
import com.example.midterm.service.ProductService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostConstruct
    public void seedingData(){
        productService.initDB();
    }

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(HttpServletRequest request) throws IOException {
        String name = request.getParameter("name");
        Double price = Double.valueOf(request.getParameter("price"));
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String color = request.getParameter("color");
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategory(Product.Category.valueOf(category));
        product.setColor(Product.Color.valueOf(color));

        Product savedProduct = productService.saveProduct(product, file);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<Product> editProduct(HttpServletRequest request) throws IOException {
        Long productId = Long.valueOf(request.getParameter("id")); // Assuming 'id' is the identifier for the product
        String name = request.getParameter("name");
        Double price = Double.valueOf(request.getParameter("price"));
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String color = request.getParameter("color");

        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");

        Product product = productService.getProductById(productId);
        product.setId(productId);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategory(Product.Category.valueOf(category));
        product.setColor(Product.Color.valueOf(color));

        Product editedProduct = productService.editProduct(product, file);
        return new ResponseEntity<>(editedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws IOException {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Product>> getProductsByColor(@PathVariable Product.Color color) {
        List<Product> products = productService.getProductsByColor(color);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Product.Category category) {
        List<Product> products = productService.getProductsByCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/sortedByPrice")
    public ResponseEntity<List<Product>> getProductSortedByPrice(@RequestParam(value = "asc", defaultValue = "true") boolean asc) {
        List<Product> products = productService.getProductSortedByPrice(asc);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
