package com.example.hrmanagementfinal.accessor;

import com.example.hrmanagementfinal.models.UserDTO;
import com.example.hrmanagementfinal.models.UserRoles;
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

    public boolean updatePassword(String userId, String newPassword) {
        String query = "update user set password = ? where userId = ?";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, userId);

            return preparedStatement.executeUpdate() == 1 ? true : false ;
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    public UserDTO getUserByEmail(String email) {
        UserDTO userDTO = null;

        String query = "SELECT userId, name, email, password, phoneNo, role from user where email = ?";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userDTO = new UserDTO();
                userDTO.setUserId(resultSet.getString(1));
                userDTO.setName(resultSet.getString(2));
                userDTO.setEmail(resultSet.getString(3));
                userDTO.setPassword(resultSet.getString(4));
                userDTO.setPhoneNo(resultSet.getString(5));
                userDTO.setRole(UserRoles.valueOf(resultSet.getString(7)));
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            return userDTO;
        }
    }
}
