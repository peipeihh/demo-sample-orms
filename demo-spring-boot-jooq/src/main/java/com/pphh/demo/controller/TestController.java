package com.pphh.demo.controller;

import static com.pphh.demo.jooq.db.mysql.Tables.EMPLOYEE;

import com.pphh.demo.DaoTester;
import com.pphh.demo.dao.EmployeeJooqDao;
import com.pphh.demo.jooq.db.mysql.tables.Employee;
import com.pphh.demo.jooq.db.mysql.tables.records.EmployeeRecord;
import com.pphh.demo.po.EmployeeEntity;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/27/2018
 */
@RestController
public class TestController {

    @Autowired
    private DSLContext dsl;

    @Autowired
    private EmployeeJooqDao employeeJooqDao;

//    @RequestMapping("test")
//    public String test() {
//        Employee e = EMPLOYEE.as("e");
//        return dsl.select().from(e).getSQL();
//    }
//
//    @RequestMapping("test2")
//    public List<Employee> test2() {
//        Result<EmployeeRecord> records = dsl.selectFrom(EMPLOYEE).fetch();
//        return records.into(Employee.class);
//    }
//
//    @RequestMapping("test3")
//    public List<Employee> test3() {
//        Employee e = EMPLOYEE.as("e");
//        Result<Record2<String, String>> record2s = dsl.select(e.FIRST_NAME, e.LAST_NAME).from(e).fetch();
//        return record2s.into(Employee.class);
//    }

    @RequestMapping("/test")
    public String test() {
        DaoTester daoTester = DaoTester.getInstance();
        daoTester.run(employeeJooqDao);
        return "spring jdbc + jooq dao test is completed, please check output log.";
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
}
