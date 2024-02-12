package com.zaga.model.productsec;

import java.util.List;

import org.eclipse.microprofile.graphql.Name;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MongoEntity(collection = "ProductGraphqlDetails", database = "TestObervability")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("id")
@ApplicationScoped
@Default
@Name("ProductGraphqlDetailsType")

public class ProductGraphqlDetails extends PanacheMongoEntity{
    public String firstname;
    public String lastname;
    public List<Address> address;
    public List<ProductCategory> productCategories;
    //public String time;
}