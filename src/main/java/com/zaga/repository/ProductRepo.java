package com.zaga.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.zaga.model.ProductDetails;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepo implements PanacheMongoRepository<ProductDetails> {

   public List<ProductDetails> findByFirstName(String firstName) {
        return list("firstname", firstName);
    }

    public List<ProductDetails> findByProductCategories_Name(String category) {
        return list("productCategories.category =?1",category);
    }
    
    public List<ProductDetails> getByCity(String city) {
        return find("address.city", city).list();
    }

    
    public List<ProductDetails> findProductsLessThan(double maxPrice) {
        return find("productCategories.price <= ?1", maxPrice).list();
    }

    
    public List<ProductDetails> findByStateOrPriceOrCategory(String state, double maxPrice, String category) {
        return find("state = ?1 or price <= ?2 or productCategories.category = ?3", state, maxPrice, category).list();
    }

    
    // public ArrayList<Document> customAggregationPipeline() {
    //     ProductRepo productRepo = new ProductRepo(); // Initialize your ProductRepo instance
    //     MongoCollection<Document> collection = productRepo.mongoCollection(); // Get the collection

    //     return collection.aggregate(Arrays.asList(
    //             Aggregates.match(Filters.eq("productCategories.category", "desiredCategory")),
    //             Aggregates.project(Projections.fields(
    //                     Projections.include("firstname", "lastname"),
    //                     Projections.elemMatch("address", Filters.eq("city", "desiredCity")),
    //                     Projections.elemMatch("productCategories", Filters.eq("category", "desiredCategory")),
    //                     Projections.include("productCategories.price")
    //             ))
    //     ))
    //     .into(new ArrayList<>());
    // }
}

