package com.zaga.serviceimplementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.Document;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.zaga.model.ProductCategory;
import com.zaga.model.ProductDetails;
import com.zaga.repository.ProductRepo;
import com.zaga.service.ProductService;

import com.mongodb.client.model.Field;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class ProductServiceImpl implements ProductService{
    @Inject
    ProductRepo productRepo;

    @Override
    public void createProduct(ProductDetails product) {
        productRepo.persist(product);
    }

     @Override
    public List<ProductDetails> getallProducts() {
        return productRepo.listAll();
    }

    @Override
    public List<ProductDetails> getByFirstName(String firstName) {
        return productRepo.findByFirstName(firstName);
    }

    @Override
    public List<ProductDetails> getProductDetailsByCity(String city) {
        return productRepo.getByCity(city);
    }

    @Override
    public List<ProductDetails> getProductDetailsByPrice(double maxPrice) {
        return productRepo.findProductsLessThan(maxPrice);
    }

    @Override
    public List<ProductDetails> getProductsByCriteria(String state, double maxPrice, String category) {
        return productRepo.findByStateOrPriceOrCategory(state, maxPrice, category);
    }

    @Override
    public List<ProductDetails> getByCategory(String category) {
        return productRepo.findByProductCategories_Name(category);
    }

    @Override
    public List<String> customAggregationPipeline(String firstname) {
    
            List<ProductDetails> products = productRepo.findByFirstName(firstname);
            return products.stream()
            
                .filter(product -> product.getProductCategories() != null && !product.getProductCategories().isEmpty())
                .map(product -> product.getProductCategories().get(0).getName())
                //.map(product -> product.getProductCategories().get(0).getPrice())
                .collect(Collectors.toList());

                // List<ProductDetails> products = productRepo.findByFirstName(firstname);
                // return products.stream()
                //     .filter(product -> product.getAddress() != null && !product.getAddress().isEmpty())
                //     .map(product -> product.getAddress().get(0).getCity())
                //     .collect(Collectors.toList());
            
        }  


    @Override
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

 


