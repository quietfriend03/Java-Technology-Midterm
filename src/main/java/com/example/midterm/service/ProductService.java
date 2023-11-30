package com.example.midterm.service;

import com.example.midterm.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Product saveProduct(Product product, MultipartFile file) throws IOException;
    Product editProduct(Product product, MultipartFile file) throws IOException;
    void deleteProduct(Long id) throws IOException;
    List<Product> getAllProduct();
    Product getProductById(Long id);
    List<Product> getProductsByColor(Product.Color color);
    List<Product> getProductsByCategory(Product.Category category);
    List<Product> getProductSortedByPrice(boolean asc);
    void initDB();
}
