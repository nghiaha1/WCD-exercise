package com.wcdexercise.wcdexercise.controller;

import com.wcdexercise.wcdexercise.entity.Customer;
import com.wcdexercise.wcdexercise.model.MySqlCustomerModel;
import com.wcdexercise.wcdexercise.model.CustomerModel;
import com.wcdexercise.wcdexercise.entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DetailCustomerServlet extends HttpServlet {

    private CustomerModel customerModel;

    public DetailCustomerServlet() {
        this.customerModel = new MySqlCustomerModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lấy tham số rollNumber(id)
        String stringID = req.getParameter("id");
        int ID = Integer.parseInt(stringID);
        // kiểm tra trong database xem có tồn tại không.
        Customer customer = customerModel.findById(ID);
        // nếu không trả về trang 404
        if (customer == null) {
            req.setAttribute("message", "Customer not found!");
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        } else {
            // nếu có trả về trang detail
            req.setAttribute("customer", customer);
            req.getRequestDispatcher("/admin/customers/detail.jsp").forward(req, resp);
        }

    }
}
