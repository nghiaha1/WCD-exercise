package com.wcdexercise3.wcdexercise3.entity;

import com.wcdexercise3.wcdexercise3.entity.base.BaseEntity;
import com.wcdexercise3.wcdexercise3.entity.myenum.CategoryStatus;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Category extends BaseEntity {
    private int ID;
    private String name;
    private CategoryStatus status;
    HashMap<String, String> errors = new HashMap<>();

    public boolean isValid(){
        checkValidate();
        return errors.size() == 0;
    }

    private void checkValidate(){
        // validate dữ liệu theo kiểu cùi bắp.
        if (name == null || name.length() == 0) {
            errors.put("name", "Please enter name");
        }
    }

    public Category() {
        this.setCreateAt(LocalDateTime.now());
        this.setUpdateAt(LocalDateTime.now());
        this.status = CategoryStatus.ACTIVE;
    }

    public Category(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.setCreateAt(LocalDateTime.now());
        this.setUpdateAt(LocalDateTime.now());
        this.status = CategoryStatus.ACTIVE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryStatus getStatus() {
        return status;
    }

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }
}
