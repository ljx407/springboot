package com.ljx.chapter5.integration;

import com.ljx.chapter5.enums.SexEnum;
import com.ljx.chapter5.model.Employee;
import com.ljx.chapter5.service.EmployService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployService employService ;

    @Test
    public void getEmployeeById() {
        Employee employee = employService.getEmployeeById(1);
        Assert.assertNotNull(employee);
        Assert.assertEquals(employee.getEmpName(),"liujingxuan");
    }

    @Test
    public void findAllTest() {
        List<Employee> employeeList = employService.findAll();
        Assert.assertNotNull(employeeList);
        Assert.assertTrue(employeeList.size() > 0);
        Assert.assertTrue(employeeList.size() == 2);
    }

    @Test
    public void findByCondition() {
        List<Employee> employees = employService.findByCondition("o", "j");
        Assert.assertNotNull(employees);
        Assert.assertTrue(employees.size() == 1);
    }

    @Test
    public void findByCondition2() {
        List<Employee> employees = employService.findByCondition("o");
        Assert.assertNotNull(employees);
        Assert.assertTrue(employees.size() == 2);
    }

    @Test
    public void findByEmpName() {
        List<Employee> employees = employService.findByEmpName("liujingxuan");
        Assert.assertNotNull(employees);
        Assert.assertTrue(employees.size() == 1);
    }

    @Test
    public void findByEmpNameLike() {
        List<Employee> employees = employService.findByEmpNameLike("%l%");
        Assert.assertNotNull(employees);
        Assert.assertTrue(employees.size() == 1);
    }

    @Test
    public void findByEmpNameLikeAndSex() {
        List<Employee> employees = employService.findByEmpNameLikeAndSex("%l%", SexEnum.FEMALE);
        Assert.assertNotNull(employees);
        Assert.assertTrue(employees.size() == 1);
    }


}
