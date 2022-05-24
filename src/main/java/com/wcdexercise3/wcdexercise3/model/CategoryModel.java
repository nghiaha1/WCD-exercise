package com.wcdexercise3.wcdexercise3.model;

import com.wcdexercise3.wcdexercise3.entity.Category;

import java.util.List;

public interface CategoryModel {
    Category save(Category obj);
    List<Category> findAll();
    Category findByID(int ID);
    Category update(int ID, Category updateObj);
    boolean delete(int ID);
}
