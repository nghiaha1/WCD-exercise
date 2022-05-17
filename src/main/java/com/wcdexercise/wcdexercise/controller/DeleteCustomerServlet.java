package com.wcdexercise.wcdexercise.controller;

import com.wcdexercise.wcdexercise.entity.Customer;
import com.wcdexercise.wcdexercise.model.MySqlCustomerModel;
import com.wcdexercise.wcdexercise.model.CustomerModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCustomerServlet extends HttpServlet {

    private CustomerModel customerModel;

    public DeleteCustomerServlet() {
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
            boolean result = customerModel.delete(ID); // xoá mềm.
            if (result) {
                resp.sendRedirect("/admin/customers/list");
            } else {
                req.setAttribute("message", "Action fails!");
                req.getRequestDispatcher("/admin/errors/500.jsp").forward(req, resp);
            }
        }
    }
}
