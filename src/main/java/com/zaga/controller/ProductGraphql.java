package com.zaga.controller;

import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import com.zaga.model.Address;
import com.zaga.model.ProductCategory;
import com.zaga.model.ProductDetails;
import com.zaga.repository.ProductRepo;
import com.zaga.service.ProductDetailsServiceQl;
import com.zaga.service.ProductService;

import jakarta.inject.Inject;
import jakarta.ws.rs.PathParam;

@GraphQLApi
public class ProductGraphql {
    @Inject
    ProductRepo productRepo;


    @Inject
    ProductDetailsServiceQl productDetailsService;

    @Query("getAllProductDetails")
    public List<ProductDetails> getAllProductDetails() {
        return productDetailsService.getAllProductDetails();
    }

    @Mutation
    public ProductDetails createProductDetails(
            @Name("firstname") String firstname,
            @Name("lastname") String lastname,
            @Name("address") List<Address> address,
            @Name("productCategories") List<ProductCategory> productCategories
    ) {
        ProductDetails productDetails = new ProductDetails(firstname, lastname, address, productCategories);
        productDetailsService.addProductDetails(productDetails);
        return productDetails;
    }

    @Query
      public List<ProductDetails> getByFirstName(@PathParam("firstName") String firstName) {
       return productDetailsService.getByFirstName(firstName);
    }
    @Query
    public List<ProductDetails> searchProductDetails(@Name("searchKeyword") String searchKeyword) {
      return productDetailsService.searchProductDetails(searchKeyword);
    }
}
