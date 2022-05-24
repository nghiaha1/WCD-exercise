package com.wcdexercise3.wcdexercise3.controller.product.admin;

import com.wcdexercise3.wcdexercise3.entity.Product;
import com.wcdexercise3.wcdexercise3.entity.myenum.ProductStatus;
import com.wcdexercise3.wcdexercise3.model.CategoryModel;
import com.wcdexercise3.wcdexercise3.model.MySqlCategoryModel;
import com.wcdexercise3.wcdexercise3.model.MySqlProductModel;
import com.wcdexercise3.wcdexercise3.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateProductServlet extends HttpServlet {

    private ProductModel productModel;
    private CategoryModel categoryModel;

    public CreateProductServlet() {
        this.productModel = new MySqlProductModel();
        this.categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // trả về form.
        req.setAttribute("categories", categoryModel.findAll());
        req.setAttribute("obj", new Product());
        req.setAttribute("action", 1);
        req.getRequestDispatcher("/admin/products/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // xử lý validate và save.
        int categoryID = Integer.parseInt(req.getParameter("categoryID"));
        int status = Integer.parseInt(req.getParameter("status"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String detail = req.getParameter("detail");
        String thumbnail = req.getParameter("thumbnail");
        double price = Double.parseDouble(req.getParameter("price"));

        Product product = new Product();
        product.setName(name);
        product.setCategoryID(categoryID);
        product.setStatus(ProductStatus.of(status));
        product.setThumbnail(thumbnail);
        product.setDescription(description);
        product.setDetail(detail);
        product.setPrice(price);

        if (!product.isValid()) {
            req.setAttribute("categories", categoryModel.findAll());
            req.setAttribute("obj", product);
            req.setAttribute("action", 1);
            req.setAttribute("errors", product.getErrors());
            req.getRequestDispatcher("/admin/products/form.jsp").forward(req, resp);
            return;
        }
        if (productModel.save(product) != null) {
            resp.sendRedirect("/admin/products/list");
        } else {
            req.getRequestDispatcher("/admin/products/form.jsp").forward(req, resp);
        }
    }
}
