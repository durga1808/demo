package com.zaga.serviceimplementation;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.zaga.model.Address;
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

    @Inject
    MongoClient mongoClient;
    
    @Override
  public void createProduct(ProductDetails product) {
       
        String unixNanoTimeStr = product.getTime();

        try {
           
            long unixNanoTime = Long.parseLong(unixNanoTimeStr);

        
            Instant instant = Instant.ofEpochSecond(0, unixNanoTime); 
            ZonedDateTime utcDateTime = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC"));

            
            ZonedDateTime istDateTime = utcDateTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        
        String formattedDateTime = istDateTime.format(formatter);
            product.setTime(formattedDateTime); 

            productRepo.persist(product);
        } catch (NumberFormatException e) {
            System.err.println("Invalid Unix nanotime format: " + unixNanoTimeStr);
        }
    }

    
    // @Override
    // public void saveProductDetails(String firstname, String lastname, List<Address> addressList, List<ProductCategory> productCategories, long unixNanoTimestamp) {

    //     // Convert the Unix nano timestamp to IST
    //     LocalDateTime istTime = convertUnixNanoToIST(unixNanoTimestamp);

    //     ProductDetails productDetails = new ProductDetails();
    //     productDetails.setFirstname(firstname);
    //     productDetails.setLastname(lastname);
    //     productDetails.setAddress(addressList);
    //     productDetails.setProductCategories(productCategories);
    //     // Set the IST LocalDateTime
    //     productDetails.setTime(istTime);
    //     productDetails.persist(); // This will persist the entity to the MongoDB database
    // }

    // private LocalDateTime convertUnixNanoToIST(LocalDateTime localDateTime) {
    //     Instant instant = Instant.ofEpochSecond(localDateTime / 1_000_000_000, localDateTime % 1_000_000_000);
    //     ZoneId istZone = ZoneId.of("Asia/Kolkata"); // IST Zone
    //     return instant.atZone(istZone).toLocalDateTime();
    // }






    @Override
    public List<ProductDetails> getallProducts() {
        List<ProductDetails> allProducts = productRepo.listAll();

        // Convert the timestamp strings in each ProductDetails object to IST and store as strings
        ZoneId istZone = ZoneId.of("Asia/Kolkata");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        allProducts.forEach(product -> {
            String timestampString = product.getTime(); // Assuming ProductDetails has a method to get the timestamp string
            ZonedDateTime utcDateTime = ZonedDateTime.parse(timestampString, formatter);
            ZonedDateTime istDateTime = utcDateTime.withZoneSameInstant(istZone);
            String istDateTimeString = istDateTime.format(formatter); // Convert to IST and format as a string
            product.setTime(istDateTimeString); // Update the timestamp string in the ProductDetails object
        });

        return allProducts;
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

    // @Override
    // public List<String> customAggregationPipeline(String firstname) {
    
    //         List<ProductDetails> products = productRepo.findByFirstName(firstname);
    //         return products.stream()
            
    //             .filter(product -> product.getProductCategories() != null && !product.getProductCategories().isEmpty())
    //             .map(product -> product.getProductCategories().get(0).getName())
    //             //.map(product -> product.getProductCategories().get(0).getPrice())
    //             .collect(Collectors.toList());

    //             // List<ProductDetails> products = productRepo.findByFirstName(firstname);
    //             // return products.stream()
    //             //     .filter(product -> product.getAddress() != null && !product.getAddress().isEmpty())
    //             //     .map(product -> product.getAddress().get(0).getCity())
    //             //     .collect(Collectors.toList());
            
    //     }  


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


    @Override
    public List<Document> customAggregationPipeline(String firstname) {
    
        MongoDatabase database = mongoClient.getDatabase("TestObervability");
        MongoCollection<Document> collection = database.getCollection("ProductDetails");
    
        // Check if documents matching the query exist
        long count = collection.countDocuments(new Document("firstname", firstname));
        System.out.println("Number of documents matching the query: " + count);
    
        List<Document> pipeline = Arrays.asList(
            new Document("$match", new Document("firstname", firstname)),
            new Document("$project", new Document()
                .append("_id", 0L)
                .append("firstname", 1L)
                .append("lastname", 1L)
                .append("city", new Document("$arrayElemAt", Arrays.asList("$address.city", 0L)))
                .append("price", new Document("$arrayElemAt", Arrays.asList("$productCategories.price", 0L)))
            )
        );
    
        // Print the pipeline
        System.out.println("Aggregation pipeline: " + pipeline);
    
        List<Document> result = collection.aggregate(pipeline, Document.class).into(new ArrayList<>());
    
        // Print the result
        System.out.println("Aggregation result: " + result);
    
        return result;
    }

    @Override
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
 


