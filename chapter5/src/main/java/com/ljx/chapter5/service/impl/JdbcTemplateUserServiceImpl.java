package com.ljx.chapter5.service.impl;

import com.ljx.chapter5.enums.SexEnum;
import com.ljx.chapter5.model.User;
import com.ljx.chapter5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
public class JdbcTemplateUserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate ;

    private RowMapper<User> getUserRowMapper() {
        return (ResultSet rs , int rowNum) -> {
            int id = rs.getInt("id");
            String note = rs.getString("note");
            String userName = rs.getString("user_name");
            int sex = rs.getInt("sex");
            SexEnum sexEnum = SexEnum.getByKey(sex);
            return User.builder().id(id).sex(sexEnum).note(note).userName(userName).build();
        };
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    /**
     *
     * 1. 不能使用jdbcTemplate.queryForObject(sql,User.class,id)该方法只针对结果为单列的情况
     * 2. jdbcTemplate.queryForObject(sql, getUserRowMapper(), id)若查询的结果为null会抛出异常
     * @param id
     * @return
     */
    @Override
    public User getUserById(Integer id) {
        String sql = "select * from t_user where id = ? ";
        List<User> result = jdbcTemplate.query(sql, getUserRowMapper(), id);
        return result.stream().findFirst().orElse(null);
//        return jdbcTemplate.queryForObject(sql, getUserRowMapper(), id);
//        return jdbcTemplate.queryForObject(sql,User.class,id);
    }

    @Override
    public List<User> findUsers(String username, String note) {
        String sql = "select * from t_user where user_name like concat('%',?,'%') and note like concat('%',?,'%')";
        return jdbcTemplate.query(sql,new Object[]{username,note},getUserRowMapper());
    }

    @Override
    public int update(User user) {
        String sql = "update t_user set user_name = ? , note = ? , sex = ? where id = ?";
        Object[] param = new Object[4];
        param[0] = user.getUserName();
        param[1] = user.getNote();
        param[2] = user.getSex().getKey();
        param[3] = user.getId();
        return jdbcTemplate.update(sql,param);
    }

    @Override
    public int insert(User user) {
        String sql = "insert into t_user(user_name,note,sex) values(?,?,?)";
        Object[] param = new Object[]{user.getUserName(),user.getNote(),user.getSex().getKey()};
        return jdbcTemplate.update(sql, param);
    }

    @Override
    public int delete(User user) {
        String sql = "delete from t_user where id = ?";
        return jdbcTemplate.update(sql, user.getId());
    }
}
