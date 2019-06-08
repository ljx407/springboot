package com.ljx.chapter5.dao;

import com.ljx.chapter5.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

@Slf4j
public class RoleDaoImpl implements RoleDao {

    public RoleDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    private SqlSessionFactory sqlSessionFactory ;

    @Override
    public Role findById(Integer id) {
        Role role = null ;
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            role = sqlSession.<Role>selectOne("com.ljx.chapter5.dao.RoleDao.findById",id);
            sqlSession.commit();
        } catch (Exception e) {
            log.error("RoleDaoImpl.findById error!",e);
        }

        return role;
    }
}
