package com.pphh.demo.controller;

import com.pphh.demo.po.EmployeeJpaEntity;
import com.pphh.demo.service.EmployeeSpringDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    EmployeeSpringDao employeeService;

    @RequestMapping("/test2")
    public EmployeeJpaEntity test() {
        return employeeService.queryById(1L);
    }

    @RequestMapping("/page")
    public Page<EmployeeJpaEntity> getByPage() {
        return employeeService.queryByPage("firstName", "test", 2, 2);
    }

}
