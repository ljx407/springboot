package com.ljx.chapter5.service;

import com.ljx.chapter5.enums.SexEnum;
import com.ljx.chapter5.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public interface UserService {
    User getUserById(Integer id) ;
    List<User> findUsers(String username,String note) ;
    int update(User user);
    int insert(User user);
    int delete(User user);

    JdbcTemplate getJdbcTemplate() ;
    default User getUserByIdWithStatementCallBack(Integer id) {
        return getJdbcTemplate().execute((Statement statement) -> {
            String sql = "select * from t_user where id = ".concat(id.toString());
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.first()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("user_name");
                String note = resultSet.getString("note");
                int sex = resultSet.getInt("sex");
                return User.builder().id(userId).userName(userName).note(note).sex(SexEnum.getByKey(sex)).build();
            }
            return null ;
        });
    }

    default  User getUserByIdWithConnectionCallBack(Integer id) {
        return getJdbcTemplate().execute((Connection con) -> {
            String sql = "select * from t_user where id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.first()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("user_name");
                String note = resultSet.getString("note");
                int sex = resultSet.getInt("sex");
                return User.builder().id(userId).userName(userName).note(note).sex(SexEnum.getByKey(sex)).build();
            }
            return null ;
        });
    }
}
