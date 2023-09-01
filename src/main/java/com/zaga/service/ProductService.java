package com.zaga.service;

import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.zaga.model.ProductDetails;

import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public interface ProductService {
    void createProduct(ProductDetails product);

    List<ProductDetails> getallProducts();

    List<ProductDetails> getByFirstName(String firstName);

    List<ProductDetails> getProductDetailsByCity(String city);

    List<ProductDetails> getProductDetailsByPrice(double maxPrice);

    List<ProductDetails> getProductsByCriteria(String state, double maxPrice, String category);

    List<ProductDetails> getByCategory(String category);

    List<String> customAggregationPipeline(String firstname);

    List<Map<String, Object>> getProductDetailsByFirstName(String firstname);
    
  
}
