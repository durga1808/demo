package com.zaga.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MongoEntity(collection = "ProductDetails", database = "TestObervability")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("id")
@ApplicationScoped
@Default
public class ProductDetails extends PanacheMongoEntity{
    public String firstname;
    public String lastname;
    public List<Address> address;
    public List<ProductCategory> productCategories;
    //public String time;
}
