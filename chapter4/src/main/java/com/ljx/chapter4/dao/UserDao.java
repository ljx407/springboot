package com.ljx.chapter4.dao;

import com.ljx.chapter4.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
@Repository
public class UserDao {

    public void insertUser(User user) {

        Connection connection = null;
        PreparedStatement preparedStatement = null ;


        try {
            // Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
//            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useSSL=false","root","root");
            connection.setAutoCommit(false);
            String insertUserSql = "insert into user(id,userName,note) values(?,?,?)";
            preparedStatement = connection.prepareStatement(insertUserSql);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getNote());

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            log.error("数据库异常",e);
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
