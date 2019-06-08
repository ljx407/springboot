package com.ljx.chapter3_1.dao;

import com.ljx.chapter3_1.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TestConfigDao {

    @Autowired
    @Qualifier("testConfigDataSource")
    private DataSource dataSource ;

    public Brand findActiveBrand() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from brand where active = ?");
            preparedStatement.setString(1, "Y");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                long oid = resultSet.getLong("id");
                String brandName = resultSet.getString("brandName");
                String active = resultSet.getString("active");
                return Brand.builder()
                        .id(oid)
                        .brandName(brandName)
                        .active(active)
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
