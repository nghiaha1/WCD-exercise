package com.wcdexercise3.wcdexercise3.model;

import com.wcdexercise3.wcdexercise3.entity.Product;
import com.wcdexercise3.wcdexercise3.entity.myenum.ProductStatus;
import com.wcdexercise3.wcdexercise3.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MySqlProductModel implements ProductModel{

    @Override
    public Product save(Product obj) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "insert into products " +
                    "(categoryID, name, description, detail, thumbnail, price, createAt, updateAt, status) " +
                    "values " +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, obj.getCategoryID());
            preparedStatement.setString(2, obj.getName());
            preparedStatement.setString(3, obj.getDescription());
            preparedStatement.setString(4, obj.getDetail());
            preparedStatement.setString(5, obj.getThumbnail());
            preparedStatement.setDouble(6, obj.getPrice());
            preparedStatement.setString(7, obj.getUpdateAt().toString());
            preparedStatement.setString(8, obj.getUpdateAt().toString());
            preparedStatement.setInt(9, obj.getStatus().getValue());
            preparedStatement.execute();
            System.out.println("Action success!");
            return obj;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from products where status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, ProductStatus.ACTIVE.getValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int ID = resultSet.getInt("ID");
                int categoryID = resultSet.getInt("categoryID");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String detail = resultSet.getString("detail");
                String thumbnail = resultSet.getString("thumbnail");
                double price = resultSet.getDouble("price");
                LocalDateTime createAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updateAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updateAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                Product obj = new Product();
                obj.setID(ID);
                obj.setCategoryID(categoryID);
                obj.setName(name);
                obj.setDescription(description);
                obj.setDetail(detail);
                obj.setPrice(price);
                obj.setThumbnail(thumbnail);
                obj.setUpdateAt(updateAt);
                obj.setStatus(ProductStatus.of(intStatus));
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Product findByID(int ID) {
        Product obj = null;
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from products where status = ? and ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, ProductStatus.ACTIVE.getValue());
            preparedStatement.setInt(2, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int categoryID = resultSet.getInt("categoryID");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String detail = resultSet.getString("detail");
                String thumbnail = resultSet.getString("thumbnail");
                double price = resultSet.getDouble("price");
                LocalDateTime createAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updateAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updateAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                obj = new Product();
                obj.setID(ID);
                obj.setCategoryID(categoryID);
                obj.setName(name);
                obj.setDescription(description);
                obj.setDetail(detail);
                obj.setPrice(price);
                obj.setThumbnail(thumbnail);
                obj.setUpdateAt(updateAt);
                obj.setStatus(ProductStatus.of(intStatus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Product update(int ID, Product updateObj) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "update products " +
                    "categoryID = ?, name = ?, description = ?, detail = ?, thumbnail = ?, price = ?, createAt = ?, updateAt = ?, status = ? where ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, updateObj.getCategoryID());
            preparedStatement.setString(2, updateObj.getName());
            preparedStatement.setString(3, updateObj.getDescription());
            preparedStatement.setString(4, updateObj.getDetail());
            preparedStatement.setString(5, updateObj.getThumbnail());
            preparedStatement.setDouble(6, updateObj.getPrice());
            preparedStatement.setString(7, updateObj.getCreateAt().toString());
            preparedStatement.setString(8, updateObj.getUpdateAt().toString());
            preparedStatement.setInt(9, updateObj.getStatus().getValue());
            preparedStatement.setInt(10, ID);
            preparedStatement.execute();
            System.out.println("Connection success!");
            return updateObj;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;    }

    @Override
    public boolean delete(int ID) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "update products " +
                    "set status = ? where ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, ProductStatus.DELETED.getValue());
            preparedStatement.setInt(2, ID);
            preparedStatement.execute();
            System.out.println("Action success!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;    }
}
