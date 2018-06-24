package com.pphh.demo.controller;

import com.pphh.demo.po.EmployeeEntity;
import com.pphh.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/24/2018
 */
@RestController
public class Hello {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/test2")
    public EmployeeEntity test() {
        return employeeService.queryById(1L);
    }

}
