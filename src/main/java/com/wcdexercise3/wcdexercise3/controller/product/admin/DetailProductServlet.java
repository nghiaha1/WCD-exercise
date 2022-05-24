package com.wcdexercise3.wcdexercise3.controller.product.admin;

import com.wcdexercise3.wcdexercise3.entity.Product;
import com.wcdexercise3.wcdexercise3.model.MySqlProductModel;
import com.wcdexercise3.wcdexercise3.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DetailProductServlet extends HttpServlet {

    private ProductModel productModel;

    public DetailProductServlet() {
        this.productModel = new MySqlProductModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lấy tham số rollNumber(id)
        String stringID = req.getParameter("id");
        int ID = Integer.parseInt(stringID);
        // kiểm tra trong database xem có tồn tại không.
        Product product = productModel.findByID(ID);
        // nếu không trả về trang 404
        if (product == null) {
            req.setAttribute("message", "Product not found!");
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        } else {
            // nếu có trả về trang detail
            req.setAttribute("product", product);
            req.getRequestDispatcher("/admin/products/detail.jsp").forward(req, resp);
        }

    }
}
