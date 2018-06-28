package com.pphh.demo.controller;

import com.pphh.demo.DaoTester;
import com.pphh.demo.dao.EmployeeJdbcDao;
import com.pphh.demo.po.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/28/2018
 */
@RestController
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmployeeJdbcDao employeeJdbcDao;

    @RequestMapping("/test")
    public String test() {
        DaoTester daoTester = DaoTester.getInstance();
        daoTester.run(employeeJdbcDao);
        return "spring jdbc dao test is completed, please check output log.";
    }

    @RequestMapping("/selectById")
    public EmployeeEntity selectById(@RequestParam(name = "id") Long id) {
        return employeeJdbcDao.selectById(id);
    }

    @RequestMapping("/insertNew")
    public Long insertNew() {
        EmployeeEntity employeeEntity = EmployeeEntity.createOne();
        return employeeJdbcDao.insert(employeeEntity);
    }

    @RequestMapping("/deleteById")
    public Boolean deleteById(@RequestParam(name = "id") Long id) {
        return employeeJdbcDao.deleteById(id);
    }

}
