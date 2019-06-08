package com.ljx.chapter5.controller;

import com.ljx.chapter5.model.Employee;
import com.ljx.chapter5.service.EmployService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/emp")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployService employService ;

    @RequestMapping("/{id}")
    @ResponseBody
        public String getEmployeeById(@PathVariable Integer id) {
        log.info("EmployeeController.getEmployeeById comming.....");
        Employee employee = employService.getEmployeeById(id);
        return employee.toString() ;
    }
}
