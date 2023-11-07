package com.zaga.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zaga.model.ProductCategory;
import com.zaga.model.ProductDetails;
import com.zaga.repository.ProductRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class ProductDetailsServiceQl {

    @Inject
     ProductRepo productRepo;

    List<ProductDetails> productDetailsList = new ArrayList<>();

    public List<ProductDetails> getAllProductDetails() {
        List<ProductDetails> allProducts = productRepo.listAll();
        return allProducts;
    }

    public void addProductDetails(ProductDetails productDetails) {
        productRepo.persist(productDetails);
    }

    public List<ProductDetails> getByFirstName(String firstName) {
        return productRepo.findByFirstName(firstName);
    }

  public List<Map<String, Object>> getProductDetailsByFirstName(String firstname) {
        List<ProductDetails> products = productRepo.findByFirstName(firstname);
        
        return products.stream()
            .filter(product -> product.getAddress() != null && !product.getAddress().isEmpty())
            .filter(product -> product.getProductCategories() != null && !product.getProductCategories().isEmpty())
            .map(product -> {
                Map<String, Object> productInfo = new HashMap<>();
                productInfo.put("city", product.getAddress().get(0).getCity());
                
                List<Double> prices = product.getProductCategories().stream()
                    .map(ProductCategory::getPrice)
                    .collect(Collectors.toList());
                productInfo.put("prices", prices);
                
                return productInfo;
            })
            .collect(Collectors.toList());
    }

}
