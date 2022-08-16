package com.example.hrmanagementfinal.accessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeAccessor {

    @Autowired
    private DataSource dataSource;

    public List<String> getEmployees(){

        List<String>employees =new ArrayList<>();

        try(Connection connection = dataSource.getConnection()) {
            String sql = "SELECT EMPLOYEE from employee";
            PreparedStatement prmt = connection.prepareStatement(sql);
            ResultSet resultSet = prmt.executeQuery();
            while (resultSet.next()) {
                employees.add(resultSet.getString(1));
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
