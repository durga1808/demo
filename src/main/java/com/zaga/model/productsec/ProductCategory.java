package com.zaga.model.productsec;

import java.util.List;

import org.eclipse.microprofile.graphql.Name;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("id")
@Name("GraphQLObjectType")
public class ProductCategory extends PanacheMongoEntity{
     public String name;
    public String description;
    public double price;
    public List<String> category;
    
}