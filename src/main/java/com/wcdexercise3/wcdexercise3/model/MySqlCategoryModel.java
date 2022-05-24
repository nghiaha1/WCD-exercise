package com.wcdexercise3.wcdexercise3.model;

import com.wcdexercise3.wcdexercise3.entity.Category;
import com.wcdexercise3.wcdexercise3.entity.myenum.CategoryStatus;
import com.wcdexercise3.wcdexercise3.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MySqlCategoryModel implements CategoryModel{

    @Override
    public Category save(Category obj) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "insert into categories " +
                    "(name, createAt, updateAt, status) " +
                    "values " +
                    "(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getCreateAt().toString());
            preparedStatement.setString(3, obj.getUpdateAt().toString());
            preparedStatement.setInt(4, obj.getStatus().getValue());
            preparedStatement.execute();
            System.out.println("Action success!");
            return obj;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from categories where status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, CategoryStatus.ACTIVE.getValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int ID = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                LocalDateTime createAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updateAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updateAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                Category obj = new Category(ID, name);
                obj.setCreateAt(createAt);
                obj.setUpdateAt(updateAt);
                obj.setStatus(CategoryStatus.of(intStatus));
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Category findByID(int ID) {
        Category obj = null;
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from categories where status = ? and ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, CategoryStatus.ACTIVE.getValue());
            preparedStatement.setInt(2, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("name");
                LocalDateTime createAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updateAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updateAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                obj = new Category(ID, name);
                obj.setCreateAt(createAt);
                obj.setUpdateAt(updateAt);
                obj.setStatus(CategoryStatus.of(intStatus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Category update(int ID, Category updateObj) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "update categories " +
                    "name = ?, createAt = ?, updateAt = ?, status = ? where ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, updateObj.getName());
            preparedStatement.setString(2, updateObj.getCreateAt().toString());
            preparedStatement.setString(3, updateObj.getUpdateAt().toString());
            preparedStatement.setInt(4, updateObj.getStatus().getValue());
            preparedStatement.setInt(9, ID);
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
            String sqlQuery = "update categories " +
                    "set status = ? where ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, CategoryStatus.DELETED.getValue());
            preparedStatement.setInt(2, ID);
            preparedStatement.execute();
            System.out.println("Action success!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;    }
}
