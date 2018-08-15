package com.pphh.demo.controller;

import com.pphh.demo.DaoTester;
import com.pphh.demo.po.EmployeeEntity;
import com.pphh.demo.service.EmployeeSpringJpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author huangyinhuang
 * @date 6/24/2018
 */
@RestController
public class TestController {

    static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    EmployeeSpringJpaDao employeeSpringDao;

    @RequestMapping("/test")
    public String test() {
        logger.info("test the spring boot data jpa dao...");
        DaoTester daoTester = DaoTester.getInstance();
        daoTester.run(employeeSpringDao);
        return "spring jpa dao test is completed, please check output log for test results.";
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
    public com.pphh.demo.po.Page<EmployeeEntity> getByPage(@RequestParam(name = "index") Integer index,
                                                           @RequestParam(name = "size") Integer size) {
        return employeeSpringDao.queryByPage(index, size);
    }

    @RequestMapping("/page/complex")
    public Page<EmployeeEntity> getByPageEx() {
        return employeeSpringDao.queryByPage("firstName", "test", 2, 5);
    }

}
