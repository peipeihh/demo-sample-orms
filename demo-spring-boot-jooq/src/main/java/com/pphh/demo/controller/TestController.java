package com.pphh.demo.controller;

import com.pphh.demo.DaoTester;
import com.pphh.demo.dao.EmployeeJooqDao;
import com.pphh.demo.po.EmployeeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TestController
 *
 * @author huangyinhuang
 * @date 6/27/2018
 */
@RestController
public class TestController {

    static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private EmployeeJooqDao employeeJooqDao;

    @RequestMapping("/test")
    public String test() {
        logger.info("test the jooq dao...");
        DaoTester daoTester = DaoTester.getInstance();
        daoTester.run(employeeJooqDao);
        return "spring boot jooq dao test is completed, please check output log for test results.";
    }

    @RequestMapping("/selectById")
    public EmployeeEntity selectById(@RequestParam(name = "id") Long id) {
        return employeeJooqDao.selectById(id);
    }

    @RequestMapping("/selectAll")
    public List<EmployeeEntity> selectAll() {
        return employeeJooqDao.selectAll();
    }

    @RequestMapping("/selectLast")
    public EmployeeEntity selectLast() {
        return employeeJooqDao.selectLast();
    }

    @RequestMapping("/count")
    public Long count() {
        return employeeJooqDao.count();
    }

    @RequestMapping("/update")
    public String update() {
        EmployeeEntity employee = employeeJooqDao.selectLast();
        String firstName = "test-" + System.currentTimeMillis();
        employee.setFirstName(firstName);
        Boolean bSuccess = employeeJooqDao.update(employee);
        return String.format("Update employee, id = %s, first name = %s, bSuccess = %s", employee.getId(), firstName, bSuccess);
    }

    @RequestMapping("/insertNew")
    public Long insertNew() {
        EmployeeEntity employeeEntity = EmployeeEntity.createOne();
        return employeeJooqDao.insert(employeeEntity);
    }

    @RequestMapping("/deleteById")
    public Boolean deleteById(@RequestParam(name = "id") Long id) {
        return employeeJooqDao.deleteById(id);
    }

    @RequestMapping("/page")
    public com.pphh.demo.po.Page<EmployeeEntity> getByPage(@RequestParam(name = "index") Integer index,
                                                           @RequestParam(name = "size") Integer size) {
        return employeeJooqDao.queryByPage(index, size);
    }
}
