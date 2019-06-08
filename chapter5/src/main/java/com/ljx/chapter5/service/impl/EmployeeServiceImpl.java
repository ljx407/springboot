package com.ljx.chapter5.service.impl;

import com.ljx.chapter5.dao.EmployeeJpaRepositoryDao;
import com.ljx.chapter5.enums.SexEnum;
import com.ljx.chapter5.model.Employee;
import com.ljx.chapter5.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployService {

    @Autowired
    private EmployeeJpaRepositoryDao employeeJpaRepositoryDao ;

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeJpaRepositoryDao.findById(id).get();
    }

    @Override
    public List<Employee> findAll() {
        return employeeJpaRepositoryDao.findAll();
    }

    @Override
    public List<Employee> findByCondition(String note,String empName) {
        return employeeJpaRepositoryDao.findByCondition(note,empName);
    }

    @Override
    public List<Employee> findByCondition(String note) {
        return employeeJpaRepositoryDao.findByCondition(note);
    }

    @Override
    public List<Employee> findByEmpName(String empName) {
        return employeeJpaRepositoryDao.findByEmpName(empName);
    }

    @Override
    public List<Employee> findByEmpNameLike(String empName) {
        return employeeJpaRepositoryDao.findByEmpNameLike(empName);
    }

    @Override
    public List<Employee> findByEmpNameLikeAndSex(String empName, SexEnum sex) {
        return employeeJpaRepositoryDao.findByEmpNameLikeAndSex(empName,sex);
    }
}
