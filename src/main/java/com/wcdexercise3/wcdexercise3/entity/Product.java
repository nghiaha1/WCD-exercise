package com.wcdexercise3.wcdexercise3.entity;

import com.wcdexercise3.wcdexercise3.entity.base.BaseEntity;
import com.wcdexercise3.wcdexercise3.entity.myenum.ProductStatus;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Product extends BaseEntity {
    private int ID; //string ID UUID
    private int categoryID;
    private String name; //slug
    private String description;
    private String detail;
    private String thumbnail;
    private double price; // or BigDecimal
    private ProductStatus status;
    HashMap<String, String> errors = new HashMap<>();

    public boolean isValid(){
        checkValidate();
        return errors.size() == 0;
    }

    private void checkValidate(){
        if (name == null || name.length() == 0) {
            errors.put("name", "Please enter name");
        }
        if (description == null || description.length() == 0) {
            errors.put("description", "Please enter description");
        }
        if (detail == null || detail.length() == 0) {
            errors.put("detail", "Please enter detail");
        }
        if (thumbnail == null || thumbnail.length() == 0) {
            errors.put("thumbnail", "Please choose thumbnail");
        }
        if (price == 0) {
            errors.put("price", "Please enter price");
        }
    }

    public Product() {
        this.name = "";
        this.description = "";
        this.detail = "";
        this.thumbnail = "";
        this.price = 0;
        this.setCreateAt(LocalDateTime.now());
        this.setUpdateAt(LocalDateTime.now());
        this.status = ProductStatus.ACTIVE;
    }

    public Product(int categoryID, String name, String description, String detail, String thumbnail, double price) {
        this.categoryID = categoryID;
        this.name = name;
        this.description = description;
        this.detail = detail;
        this.thumbnail = thumbnail;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }
}