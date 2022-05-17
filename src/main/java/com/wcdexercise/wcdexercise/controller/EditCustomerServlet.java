package com.wcdexercise.wcdexercise.controller;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.wcdexercise.wcdexercise.entity.Customer;
import com.wcdexercise.wcdexercise.model.MySqlCustomerModel;
import com.wcdexercise.wcdexercise.model.CustomerModel;
import com.wcdexercise.wcdexercise.util.DateTimeHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class EditCustomerServlet extends HttpServlet {

    private CustomerModel customerModel;

    public EditCustomerServlet() {
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
            req.setAttribute("message", "Student not found!");
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        } else {
            // nếu có trả về trang detail
            req.setAttribute("customer", customer);
            req.setAttribute("action", 2);
            req.getRequestDispatcher("/admin/customers/form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // xử lý validate và save.
        String stringID = req.getParameter("id");
        int ID = Integer.parseInt(stringID);
        Customer existingCustomer = customerModel.findById(ID);
        if(existingCustomer == null){
            req.setAttribute("message", "Customer not found!");
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        }else{
            String name = req.getParameter("name");
            String phone = req.getParameter("phone");
            String img = req.getParameter("img");
            String stringBirthday = req.getParameter("dob");
            LocalDateTime birthday = DateTimeHelper.convertStringToLocalDateTime(stringBirthday);
            Customer customer = new Customer(name, phone,img, birthday);
            // validate dữ liệu
            if (customerModel.update(ID,customer) != null) {
                resp.sendRedirect("/admin/students/list");
            } else {
                // nếu có trả về trang form
                req.setAttribute("customer", customer);
                req.setAttribute("action", 2);
                req.getRequestDispatcher("/admin/customers/form.jsp").forward(req, resp);
            }
        }
    }
}
