package com.pphh.demo.controller;

import static com.pphh.demo.jooq.db.mysql.Tables.EMPLOYEE;

import com.pphh.demo.jooq.db.mysql.tables.Employee;
import com.pphh.demo.jooq.db.mysql.tables.records.EmployeeRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    DSLContext dsl;

    @RequestMapping("test")
    public String test() {
        Employee e = EMPLOYEE.as("e");
        return dsl.select().from(e).getSQL();
    }

    @RequestMapping("test2")
    public List<Employee> test2() {
        Result<EmployeeRecord> records = dsl.selectFrom(EMPLOYEE).fetch();
        return records.into(Employee.class);
    }

    @RequestMapping("test3")
    public List<Employee> test3() {
        Employee e = EMPLOYEE.as("e");
        Result<Record2<String, String>> record2s = dsl.select(e.FIRST_NAME, e.LAST_NAME).from(e).fetch();
        return record2s.into(Employee.class);
    }
}
