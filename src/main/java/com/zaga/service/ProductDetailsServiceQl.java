package com.zaga.service;

import java.util.ArrayList;
import java.util.List;
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

}
