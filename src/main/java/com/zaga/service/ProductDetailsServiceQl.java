package com.zaga.service;

import java.util.ArrayList;
import java.util.List;

import com.zaga.model.ProductDetails;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductDetailsServiceQl {
      List<ProductDetails> productDetailsList = new ArrayList<>();

    public List<ProductDetails> getAllProductDetails() {
        return productDetailsList;
    }

    public void addProductDetails(ProductDetails productDetails) {
        productDetailsList.add(productDetails);
    }
}
