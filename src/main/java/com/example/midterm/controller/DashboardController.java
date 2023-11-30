package com.example.midterm.controller;

import com.example.midterm.model.Product;
import com.example.midterm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class DashboardController {
    @Autowired
    private ProductService productService;

    @GetMapping("/dashboard")
    public String showMainPage(Model model){
        List<Product.Category> categories = List.of(Product.Category.values());
        Map<Product.Category, List<Product>> productsInCategory = new HashMap<>();
        for(Product.Category category: categories){
            List<Product> productsByCategory = productService.getProductsByCategory(category);
            productsInCategory.put(category, productsByCategory);
        }
        System.out.println(productsInCategory.get("Phone"));
        model.addAttribute("categories", categories);
        model.addAttribute("productsInCategory", productsInCategory);
        return "dashboard";
    }

    @GetMapping("/detail/{id}")
    public String showDetailPage(@PathVariable Long id, Model model){
        Product product = productService.getProductById(id);
        model.addAttribute("detailProduct", product);
        return "detail";
    }

    @GetMapping("all-product")
    public String showAllProduct(Model model,
                                 @RequestParam(name = "category", required = false) String category,
                                 @RequestParam(name = "color", required = false) String color,
                                 @RequestParam(name = "sortBy", defaultValue = "asc") String sortBy){
        List<Product.Category> categories = List.of(Product.Category.values());
        List<Product.Color> colors = List.of(Product.Color.values());
        List<Product> allProducts = productService.getAllProduct();
        List<Product> filteredProducts = new ArrayList<>(allProducts);
        if(category != null && !category.isEmpty()){
            filteredProducts.retainAll(productService.getProductsByCategory(Product.Category.valueOf(category)));
        }

        if(color != null && !color.isEmpty()){
            filteredProducts.retainAll(productService.getProductsByColor(Product.Color.valueOf(color)));
        }

        switch (sortBy){
            case "desc":
                Collections.sort(filteredProducts, Comparator.comparing(Product::getPrice).reversed());
                break;
            case "asc":
                Collections.sort(filteredProducts, Comparator.comparing(Product::getPrice));
                break;
        }
        model.addAttribute("category", category);
        model.addAttribute("color", color);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("categories", categories);
        model.addAttribute("colors", colors);
        model.addAttribute("products", filteredProducts);
        return "all-product";
    }


}
