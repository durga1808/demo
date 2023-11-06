package com.zaga.controller;

import java.util.List;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

import com.zaga.model.ProductDetails;
import com.zaga.service.ProductService;

import jakarta.inject.Inject;

@GraphQLApi
public class ProductGraphql {
    
    @Inject
    ProductService productService;

    @Query("allProducts")
    @Description("Get all products.")
    public List<ProductDetails> getAllProducts() {
        return productService.getallProducts();
    } 
}
