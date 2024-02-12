package com.zaga.repository;

import com.zaga.model.productsec.ProductGraphqlDetails;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ProductGraphqlRepo  implements PanacheMongoRepository<ProductGraphqlDetails>{
    
}
