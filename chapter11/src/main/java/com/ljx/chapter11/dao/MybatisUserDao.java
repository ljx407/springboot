package com.ljx.chapter11.dao;

import com.ljx.chapter11.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisUserDao {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public User findUserById(Long id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("com.ljx.chapter11.dao.MybatisUserDao.findUserById", id);
        sqlSession.commit();
        sqlSession.close();
        return user ;
    }

}
