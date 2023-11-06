// package com.zaga.kafka;

// import java.util.List;

// import com.zaga.model.ProductDetails;
// import com.zaga.service.ProductService;

// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.inject.Inject;
// import jakarta.ws.rs.GET;
// import jakarta.ws.rs.Path;


// @ApplicationScoped
// @Path("/kafka-consumer")
// public class KafkaConsumer {

//     @Inject
//     private KafkaConsumerService kafkaConsumerService; 

//      @Inject 
//      private ProductService productService;

//     @GET
//     public List<ProductDetails> getProductDetails() {
//         List<ProductDetails> product = kafkaConsumerService.getProductDetails();
//         productService.createProductDetails(product);
//         return product;
//     }
// }

