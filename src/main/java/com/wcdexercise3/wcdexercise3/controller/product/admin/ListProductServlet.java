package com.wcdexercise3.wcdexercise3.controller.product.admin;

import com.wcdexercise3.wcdexercise3.entity.Product;
import com.wcdexercise3.wcdexercise3.model.CategoryModel;
import com.wcdexercise3.wcdexercise3.model.MySqlCategoryModel;
import com.wcdexercise3.wcdexercise3.model.MySqlProductModel;
import com.wcdexercise3.wcdexercise3.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListProductServlet extends HttpServlet {

    private ProductModel productModel;

    public ListProductServlet() {
        this.productModel = new MySqlProductModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> list = productModel.findAll();
        req.setAttribute("title", "List Product");
        req.setAttribute("list", list);
        req.getRequestDispatcher("/admin/products/list.jsp").forward(req,resp);
    }
}
