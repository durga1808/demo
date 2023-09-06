package com.zaga.kafka;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.reactive.messaging.Incoming;


import com.zaga.model.ProductDetails;

import jakarta.enterprise.context.ApplicationScoped;



@ApplicationScoped
public class KafkaConsumerService {

 private List<ProductDetails> productDetailsList = new ArrayList();

    @Incoming("product") // Replace with your actual Kafka topic name
    public void consumeProductDetails(ProductDetails productDetails) {
        // Process the received ProductDetails and add it to the list
        productDetailsList.add(productDetails);
    }

    public List<ProductDetails> getProductDetails() {
        // Return the list of ProductDetails data
        return productDetailsList;
    }
}