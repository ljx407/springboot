package com.ljx.chapter6.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {

    @Autowired
    private PlatformTransactionManager txManager;

    @Autowired
    private DataSource dataSource ;

    @Test
    public void testPlatformTransactionManager() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);
        Connection connection = DataSourceUtils.getConnection(dataSource);

        try{
            String INSERT_SQL = "insert t_role(roleName) values (?)";
            PreparedStatement pstmt = connection.prepareStatement(INSERT_SQL);
            pstmt.setString(1, "test");
            pstmt.execute();
//            txManager.commit(status);
            throw new IllegalStateException();
        }catch(Exception ex){
            status.setRollbackOnly();
            txManager.rollback(status);
        }finally{
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

    }
}
