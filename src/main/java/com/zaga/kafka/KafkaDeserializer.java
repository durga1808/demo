// package com.zaga.kafka;

// import org.apache.kafka.common.serialization.Deserializer;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.zaga.model.ProductDetails;

// public class KafkaDeserializer implements Deserializer<ProductDetails> {

//     private final ObjectMapper objectMapper;

//     public KafkaDeserializer() {
//        this.objectMapper = new ObjectMapper();
//     }
 
//     @Override
//     public ProductDetails deserialize(String topic, byte[] data) {
//        try {
//           return objectMapper.readValue(data, ProductDetails.class);
//        } catch (Exception e) {
//           throw new RuntimeException("Error deserializing JSON", e);
//        }
//     }
//     }


