package com.pphh.demo.controller;

import com.pphh.demo.DaoTester;
import com.pphh.demo.dao.EmployeeJdbcDao;
import com.pphh.demo.po.EmployeeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author huangyinhuang
 * @date 6/28/2018
 */
@RestController
public class TestController {

    static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private EmployeeJdbcDao employeeJdbcDao;

    @RequestMapping("/test")
    public String test() {
        logger.info("test the jdbc dao...");
        DaoTester daoTester = DaoTester.getInstance();
        daoTester.run(employeeJdbcDao);
        return "spring boot jdbc dao test is completed, please check output log for test results.";
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
