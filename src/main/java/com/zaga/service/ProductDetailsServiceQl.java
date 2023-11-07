package com.zaga.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zaga.model.ProductDetails;
import com.zaga.repository.ProductRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class ProductDetailsServiceQl {

    @Inject
     ProductRepo productRepo;
  @Inject
    MongoClient mongoClient;
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

       public List<Document> aggregateDocuments(String firstname) {
        MongoDatabase database = mongoClient.getDatabase("TestObervability");
        MongoCollection<Document> collection = database.getCollection("ProductDetails");

        List<Document> pipeline = Arrays.asList(
            new Document("$match", new Document("firstname", firstname)),
            new Document("$project", new Document()
                .append("_id", 0L)
                .append("firstname", 1L)
                .append("lastname", 1L)
                .append("city", new Document("$arrayElemAt", Arrays.asList("$address.city", 0L)))
                .append("state", new Document("$arrayElemAt", Arrays.asList("$address.state", 0L)))
                .append("name", new Document("$arrayElemAt", Arrays.asList("$productCategories.name", 0L)))
                .append("price", new Document("$arrayElemAt", Arrays.asList("$productCategories.price", 0L)))
            )
        );

        List<Document> result = collection.aggregate(pipeline, Document.class).into(new ArrayList<>());
        return result;
       
    }
}
