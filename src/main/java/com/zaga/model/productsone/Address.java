package com.zaga.model.productsone;

import org.eclipse.microprofile.graphql.Name;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("id")
@Name("'ProductOne")

public class Address {
    public String address1;
    public String address2;
    public String city;
    public String state;
    public String pincode;
}
