package com.ljx.chapter5.dao;

import com.ljx.chapter5.enums.SexEnum;
import com.ljx.chapter5.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Repository
public class UserDao {

    @Autowired
    private DataSource dataSource ;

    public User getUserById(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null ;
        try {
            String sql = "select * from t_user where id = ?";
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("user_name");
                String note = resultSet.getString("note");
                int sex = resultSet.getInt("sex");

                return User.builder().id(userId).note(note).userName(userName).sex(SexEnum.getByKey(sex)).build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                log.error("UserDao.getUserById error!!!",e);
            }
        }

        return null ;
    }
}
