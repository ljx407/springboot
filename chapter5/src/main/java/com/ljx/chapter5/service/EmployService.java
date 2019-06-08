package com.ljx.chapter5.service;

import com.ljx.chapter5.enums.SexEnum;
import com.ljx.chapter5.model.Employee;

import java.util.List;

public interface EmployService {
    Employee getEmployeeById(Integer id) ;
    List<Employee> findAll();
    List<Employee> findByCondition(String note,String empName);
    List<Employee> findByCondition(String note);
    List<Employee> findByEmpName(String empName);
    List<Employee> findByEmpNameLike(String empName);
    List<Employee> findByEmpNameLikeAndSex(String empName, SexEnum sex);
}
