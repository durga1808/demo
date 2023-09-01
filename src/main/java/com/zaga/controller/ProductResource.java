package com.zaga.controller;

import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.zaga.model.ProductDetails;
import com.zaga.repository.ProductRepo;
import com.zaga.service.ProductService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/productview")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductRepo productRepo;

    @Inject
    ProductService productService;
    
    @POST
    @Path("/create")
    public Response createProduct(ProductDetails product) {
        try {
            //System.out.println("----------------");
            productService.createProduct(product);
            return Response.status(200).entity(product).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    // @POST
    // @Path("/create/{timeUnixNano}")
    // public Response createProductWithUnix(ProductDetails product, @PathParam("timeUnixNano") String timeUnixNano) {
    //     try {
    //         //System.out.println("----------------");
    //         productService.createProduct(product);
    //         return Response.status(200).entity(product).build();
    //     } catch (Exception e) {
    //         return Response.status(500).entity(e.getMessage()).build();
    //     }
    // }

    // @POST
    // @Path("/peristtime")
    // public Response saveProductDetails(ProductDetails productDetails) {
    //     try {
    //         productService.saveProductDetails(null, null, null, null, 0);
    //         return Response.status(200).entity(productDetails).build();
    //     } catch (Exception e) {
    //         return Response.status(500).entity(e.getMessage()).build();
    //     }
    // }

    @GET
    @Path("/listallproductdetails")
    public Response getAllProducts() {
      try {
          productService.getallProducts();
        return Response.status(200).entity(productService).build();
      } catch (Exception e) {
          return Response.status(500).entity(e.getMessage()).build();
      }
    }

     @GET
    @Path("/byFirstNamegetalldetails/{firstName}")
    public List<ProductDetails> getByFirstName(@PathParam("firstName") String firstName) {
        //productRepo.mongoCollection().
        return productService.getByFirstName(firstName);
    }

   
    @GET
    @Path("/byCity/{city}")
    public List<ProductDetails> getByCity(@PathParam("city") String city) {
        return productService.getProductDetailsByCity(city);
    }

    @GET
    @Path("/by-price/{price}")
    public List<ProductDetails> getProductDetailsByPrice(@PathParam("price") double price) {
        return productService.getProductDetailsByPrice(price);
    }


    @GET
    @Path("/search")
    public Response searchProducts(
            @QueryParam("state") String state,
            @QueryParam("maxPrice") double maxPrice,
            @QueryParam("category") String category) {
        List<ProductDetails> products = productService.getProductsByCriteria(state, maxPrice, category);
        return Response.status(200).entity(products).build();
    }

        @GET
        @Path("/getbycategory/{category}")
        public List<ProductDetails> getByCategory(@PathParam("category") String category) {
            return productService.getByCategory(category);
        }

        @GET
        @Path("/aggregation/{firstname}")
      public List<Document> customAggregationEndpoint(@PathParam("firstname") String firstname) {
            return productService.customAggregationPipeline(firstname);
        } 
        
        
        @GET
        @Path("/getspecificinfo/{firstname}")
        public List<Map<String, Object>> getProductInfoByProductName(@PathParam("firstname") String firstname) {
            return productService.getProductDetailsByFirstName(firstname);
        }
    }