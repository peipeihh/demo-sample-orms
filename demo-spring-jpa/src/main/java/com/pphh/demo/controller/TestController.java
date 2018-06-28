package com.pphh.demo.controller;

import com.pphh.demo.DaoTester;
import com.pphh.demo.po.EmployeeEntity;
import com.pphh.demo.service.EmployeeSpringJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/24/2018
 */
@RestController
public class TestController {

    @Autowired
    EmployeeSpringJpaDao employeeSpringDao;

    @RequestMapping("/test")
    public String test() {
        DaoTester daoTester = DaoTester.getInstance();
        daoTester.run(employeeSpringDao);
        return "spring jpa dao test is completed, please check output log.";
    }

    @RequestMapping("/selectById")
    public EmployeeEntity selectById(@RequestParam(name = "id") Long id) {
        return employeeSpringDao.selectById(id);
    }

    @RequestMapping("/selectLast")
    public EmployeeEntity selectById() {
        return employeeSpringDao.selectLast();
    }

    @RequestMapping("/page")
    public Page<EmployeeEntity> getByPage() {
        return employeeSpringDao.queryByPage("firstName", "test", 2, 2);
    }

}
