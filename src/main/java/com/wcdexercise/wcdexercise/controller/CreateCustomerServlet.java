package com.wcdexercise.wcdexercise.controller;

import com.wcdexercise.wcdexercise.entity.Customer;
import com.wcdexercise.wcdexercise.model.CustomerModel;
import com.wcdexercise.wcdexercise.model.MySqlCustomerModel;
import com.wcdexercise.wcdexercise.util.DateTimeHelper;
import com.wcdexercise.wcdexercise.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class CreateCustomerServlet extends HttpServlet {

    private CustomerModel customerModel;

    public CreateCustomerServlet() {
        this.customerModel = new MySqlCustomerModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // trả về form.
        req.setAttribute("customer", new Customer());
        req.setAttribute("action", 1);
        req.getRequestDispatcher("/admin/customers/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // xử lý validate và save.
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String img = req.getParameter("img");
        String stringBirthday = req.getParameter("dob");
        Customer customer = new Customer(name, phone, img);
        HashMap<String, String> errors = new HashMap<>();
        if (stringBirthday != null && stringBirthday.length() > 0) {
            LocalDateTime birthday = DateTimeHelper.convertStringToLocalDateTime(stringBirthday);
            customer.setDob(birthday);
        }
        // validate dữ liệu theo kiểu cùi bắp.
        if (name == null || name.length() == 0) {
            errors.put("fullName", "Please enter name");
        }
        if (phone == null || phone.length() == 0) {
            errors.put("phone", "Please enter phone");
        }
        if (img == null || img.length() == 0) {
            errors.put("phone", "Please enter image");
        }
        if (errors.size() > 0) {
            req.setAttribute("customer", customer);
            req.setAttribute("action", 1);
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/admin/students/form.jsp").forward(req, resp);
            return;
        }
        if (customerModel.save(customer) != null) {
            resp.sendRedirect("/admin/customers/list");
        } else {
            req.getRequestDispatcher("/admin/customers/form.jsp").forward(req, resp);
        }
    }
}
