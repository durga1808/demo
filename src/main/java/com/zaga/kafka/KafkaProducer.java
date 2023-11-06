// package com.zaga.kafka;

// import com.zaga.model.ProductDetails;
// import com.zaga.service.ProductService;

// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.inject.Inject;
// import jakarta.ws.rs.Consumes;
// import jakarta.ws.rs.POST;
// import jakarta.ws.rs.Path;
// import jakarta.ws.rs.Produces;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;



// @ApplicationScoped
// @Path("/product-details")
// @Produces(MediaType.APPLICATION_JSON)
// @Consumes(MediaType.APPLICATION_JSON)
// public class KafkaProducer {

//     @Inject
//     private KafkaProducerService kafkaProducerService;

   
 
//     @POST
//     public Response sendProductDetails(ProductDetails productDetails) {
//         kafkaProducerService.sendProductDetails(productDetails);
      
//         return Response.ok().build();
//     }
// }


// // @Path("/kafka")
// // @ApplicationScoped
// // public class KafkaProducer {
    
// //     @Channel("logs-out")
// //     Emitter<String> emitter;

// //     @POST
// //     @Consumes(MediaType.APPLICATION_JSON)
// //     public Response produceData(ProductDetails data) {
// //         String jsonData = serializeToJson(data);
// //         emitter.send(jsonData);
// //         return Response.accepted().build();
// //     }

// //     private String serializeToJson(ProductDetails data) {
// //                 return ""; 
// //     }
// // }

    
