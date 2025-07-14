package com.data.session05.modal.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;

import java.util.List;

@Data
public class ProductList {

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Product> products;

    public ProductList(List<Product> products) {
        this.products = products;
    }
}
