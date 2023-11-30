package com.example.midterm.service.implementation;

import com.example.midterm.model.Product;
import com.example.midterm.repository.ProductRepository;
import com.example.midterm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Value("${upload.directory}")
    private String uploadDirectory;

    @Override
    public Product saveProduct(Product product, MultipartFile file) throws IOException {
        saveImage(product, file);
        return productRepository.save(product);
    }

    @Override
    public Product editProduct(Product product, MultipartFile file) throws IOException {
        Product existedProduct = productRepository.findById(product.getId()).get();
        existedProduct.setName(product.getName());
        existedProduct.setPrice(product.getPrice());
        existedProduct.setDescription(product.getDescription());
        saveImage(existedProduct, file);
        existedProduct.setCategory(product.getCategory());
        existedProduct.setColor(product.getColor());
        return productRepository.save(existedProduct);
    }

    @Override
    public void deleteProduct(Long id) throws IOException {
        Product existedProduct = productRepository.findById(id).get();
        deleteImage(existedProduct);
        productRepository.delete(existedProduct);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getProductsByColor(Product.Color color) {
        return productRepository.findByColor(color);
    }

    @Override
    public List<Product> getProductsByCategory(Product.Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> getProductSortedByPrice(boolean asc) {
        return asc
                ? productRepository.findAllByOrderByPriceAsc()
                : productRepository.findAllByOrderByPriceDesc();
    }
    private void saveImage(Product product, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            Path uploadPath = Paths.get(uploadDirectory);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            // Save the image file to the local directory
            String filename = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDirectory, filename);
            Files.write(filePath, file.getBytes());

            product.setImageUrl("/uploads/" + filename);
        }
    }

    private void deleteImage(Product product) throws  IOException{
        if(product.getImageUrl() != null){
            Path filePath = Paths.get(product.getImageUrl());
            if(Files.exists(filePath)){
                Files.delete(filePath);
            }
        }
    }
}
