package com.wcdexercise3.wcdexercise3.model;

import com.wcdexercise3.wcdexercise3.entity.Product;

import java.util.List;

public interface ProductModel {
    Product save(Product obj);
    List<Product> findAll();
    Product findByID(int ID);
    Product update(int ID, Product updateObj);
    boolean delete(int ID);
}
