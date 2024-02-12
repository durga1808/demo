// package com.zaga.controller;

// import java.util.List;

// import org.eclipse.microprofile.graphql.GraphQLApi;
// import org.eclipse.microprofile.graphql.Mutation;
// import org.eclipse.microprofile.graphql.Name;
// import org.eclipse.microprofile.graphql.Query;

// import com.zaga.model.productsec.Address;
// import com.zaga.model.productsec.ProductCategory;
// import com.zaga.model.productsec.ProductGraphqlDetails;
// import com.zaga.repository.ProductGraphqlRepo;
// import com.zaga.service.ProductGraphqlService;

// import jakarta.inject.Inject;
// @GraphQLApi
// public class ProductDetailsGraphql {
    
//      @Inject
//     ProductGraphqlRepo productGraphqlRepo;

//      @Inject
//      ProductGraphqlService productGraphqlService;
 

//       @Query("getroductDetails")
//     public List<ProductGraphqlDetails> getAllProductDetails() {
//         return productGraphqlService.getAllProductDetails();
//     }

//     @Mutation
//     public ProductGraphqlDetails createProductDetails(
//             @Name("firstname") String firstname,
//             @Name("lastname") String lastname,
//             @Name("address") List<Address> address,
//             @Name("productCategories") List<ProductCategory> productCategories
//     ) {
//         ProductGraphqlDetails productGraphqlDetails = new ProductGraphqlDetails(firstname,lastname,address,productCategories);
//         productGraphqlService.addProductDetails(productGraphqlDetails);
//         return productGraphqlDetails;
//     }


// }


    

   