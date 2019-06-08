package com.ljx.chapter5.dao;

import com.ljx.chapter5.enums.SexEnum;
import com.ljx.chapter5.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeJpaRepositoryDao extends JpaRepository<Employee,Integer> {

    @Query("from Employee e where e.note like %?1% and e.empName like %?2%")
    List<Employee> findByCondition(String note,String empName) ;

    @Query("from Employee where note like %:note%")
    List<Employee> findByCondition(@Param("note") String n);

    List<Employee> findByEmpName(String empName);

    List<Employee> findByEmpNameLike(String empName);

    List<Employee> findByEmpNameLikeAndSex(String empName, SexEnum sex);
}
