package com.example.midterm.service.implementation;

import com.example.midterm.model.Product;
import com.example.midterm.repository.ProductRepository;
import com.example.midterm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public void initDB() {
        Product product1 = new Product();
        product1.setName("Iphone 15");
        product1.setPrice(799.0);
        product1.setDescription("Newest Phone");
        product1.setColor(Product.Color.Black);
        product1.setCategory(Product.Category.Phone);
        product1.setImageUrl("../uploads/iphone15.jpg");
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Ipad Air 4");
        product2.setPrice(599.0);
        product2.setDescription("Newest Tablet");
        product2.setColor(Product.Color.Pink);
        product2.setCategory(Product.Category.Tablet);
        product2.setImageUrl("../uploads/ipadair4.png");
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("Ipad Gen 10");
        product3.setPrice(699.0);
        product3.setDescription("Newest Tablet");
        product3.setColor(Product.Color.Blue);
        product3.setCategory(Product.Category.Tablet);
        product3.setImageUrl("../uploads/ipadgen10.jpg");
        productRepository.save(product3);


        Product product4 = new Product();
        product4.setName("Ipad Mini");
        product4.setPrice(699.0);
        product4.setDescription("Newest Tablet");
        product4.setColor(Product.Color.Red);
        product4.setCategory(Product.Category.Tablet);
        product4.setImageUrl("../uploads/ipadmini.jpg");
        productRepository.save(product4);

        Product product5 = new Product();
        product5.setName("Samsung S23");
        product5.setPrice(849.0);
        product5.setDescription("Newest Phone");
        product5.setColor(Product.Color.Blue);
        product5.setCategory(Product.Category.Phone);
        product5.setImageUrl("../uploads/samsungs23.png");
        productRepository.save(product5);

        Product product6 = new Product();
        product6.setName("Iphone 15 Pro Max");
        product6.setPrice(999.0);
        product6.setDescription("Newest Phone");
        product6.setColor(Product.Color.Pink);
        product6.setCategory(Product.Category.Phone);
        product6.setImageUrl("../uploads/iphone15promax.png");
        productRepository.save(product6);

        Product product7 = new Product();
        product7.setName("Samsung S10");
        product7.setPrice(499.0);
        product7.setDescription("Newest Phone");
        product7.setColor(Product.Color.Red);
        product7.setCategory(Product.Category.Phone);
        product7.setImageUrl("../uploads/samsungs10.jpg");
        productRepository.save(product7);

        Product product8 = new Product();
        product8.setName("Ipad Pro 11");
        product8.setPrice(749.0);
        product8.setDescription("Newest Tablet");
        product8.setColor(Product.Color.Red);
        product8.setCategory(Product.Category.Tablet);
        product8.setImageUrl("../uploads/ipadpro11.png");
        productRepository.save(product8);
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

            product.setImageUrl("../uploads/" + filename);
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
