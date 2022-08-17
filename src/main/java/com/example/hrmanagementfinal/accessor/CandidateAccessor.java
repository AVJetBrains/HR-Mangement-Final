package com.example.hrmanagementfinal.accessor;

import com.example.hrmanagementfinal.models.CandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CandidateAccessor {


    @Autowired
    private DataSource dataSource;


    public CandidateDTO findByName(String Name) {
        String sql = "SELECT Name, Experience, Skills from candidate where Name = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setString(1, Name);
            ResultSet resultSet = psmt.executeQuery();
            if (resultSet.next()) {
                return new CandidateDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
