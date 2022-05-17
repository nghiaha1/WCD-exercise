package com.wcdexercise.wcdexercise.model;

import com.wcdexercise.wcdexercise.entity.Customer;

import java.util.List;

public interface CustomerModel {
    Customer save(Customer customer);

    List<Customer> findAll();


    Customer findById(int ID);

    Customer update(int ID, Customer updateCustomer);

    boolean delete(int ID);
}
