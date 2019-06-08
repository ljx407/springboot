package com.ljx.chapter11.dao;

import com.ljx.chapter11.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class JdbcUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> rowMapper = (ResultSet rs, int rowNum) -> {
        long id = rs.getLong("id");
        String username = rs.getString("username");
        String memo = rs.getString("memo");
        return User.builder().id(id).userName(username).memo(memo).build();
    };

    public User findUserById(Long id) {
        return jdbcTemplate.queryForObject("select * from t_user where id = ?", rowMapper ,id);
    }

}
