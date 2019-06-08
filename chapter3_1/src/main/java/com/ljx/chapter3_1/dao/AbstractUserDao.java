package com.ljx.chapter3_1.dao;

import com.ljx.chapter3_1.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractUserDao implements UserDao {


    @Override
    public User findOne(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getDataSource().getConnection();
            preparedStatement = connection.prepareStatement("select * from user where id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                long userId = resultSet.getLong("id");
                String userName = resultSet.getString("userName");
                String note = resultSet.getString("note");
                return User.builder()
                        .id(userId)
                        .userName(userName)
                        .note(note)
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

    abstract DataSource getDataSource();
}
