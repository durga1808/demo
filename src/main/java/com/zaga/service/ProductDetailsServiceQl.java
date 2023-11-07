package com.zaga.service;

import java.util.ArrayList;
import java.util.List;
import com.zaga.model.ProductDetails;
import com.zaga.repository.ProductRepo;

import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductDetailsServiceQl {
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

    
    public List<ProductDetails> searchProductDetails(String keyword) {
        // Call the repository method to perform the search
        return productRepo.findByKeyword(keyword);
    }
  

}
