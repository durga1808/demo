package com.zaga.kafka;

import java.util.List;

import com.zaga.model.ProductDetails;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;


@ApplicationScoped
@Path("/kafka-consumer")
public class KafkaConsumer {

    @Inject
    private KafkaConsumerService kafkaConsumerService; 

    @GET
    public List<ProductDetails> getProductDetails() {
        return kafkaConsumerService.getProductDetails();
    }
}
// public class KafkaConsumer {
//     @Incoming("product")
//     public void consumeData(String jsonData) {
//         ProductDetails data = deserializeJson(jsonData);

//         System.out.println("Received data: " + data);
//     }

//     private ProductDetails deserializeJson(String jsonData) {
       

//         return null; 
//     }

