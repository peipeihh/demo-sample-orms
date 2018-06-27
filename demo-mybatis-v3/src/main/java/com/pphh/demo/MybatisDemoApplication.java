package com.pphh.demo;


import com.pphh.demo.dynamic.dao.EmployeeMybatisDynamicDao;
//import com.pphh.demo.dynamic.po.SimpleTableRecord;
import com.pphh.demo.po.LastName;
import com.pphh.demo.xml.dao.EmployeeMybatisDao;
import com.pphh.demo.po.EmployeeMybatisEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/15/2018
 */
public class MybatisDemoApplication {

    static Logger logger = LoggerFactory.getLogger(MybatisDemoApplication.class);

    public static void main(String[] args) {
        logger.info("hello,world");

        EmployeeMybatisDynamicDao mybatisDao = new EmployeeMybatisDynamicDao();
        List<EmployeeMybatisEntity> records1 = mybatisDao.readCount();
        logger.info("count = " + records1.size());

        List<EmployeeMybatisEntity> records2 = mybatisDao.readUserIdOne();
        printUserRecord(records2);

        List<EmployeeMybatisEntity> records3 = mybatisDao.readAll();
        printUserRecord(records3);


        EmployeeMybatisDao employeeDao = new EmployeeMybatisDao();
        EmployeeMybatisEntity employee = employeeDao.getById(1L);
        printEmployeeInfo(employee);

        employeeDao.update(employee);

        List<EmployeeMybatisEntity> employees = employeeDao.readAll();
        printUserRecord(employees);

        EmployeeMybatisEntity newEmployee = new EmployeeMybatisEntity();
        newEmployee.setFirstName("test");
        newEmployee.setLastName(LastName.of("test"));
        newEmployee.setBirthDate(new Date());
        newEmployee.setEmployed(Boolean.TRUE);
        employeeDao.insert(employee);

        long id = employee.getId();
        employeeDao.delete(id);

        int total = employeeDao.count();
        logger.info("total = " + total);

    }

    public static void printUserRecord(List<EmployeeMybatisEntity> records) {
        if (records != null) {
            for (int i = 0; i < records.size(); i++) {
                if (i == 0) {
                    logger.info("id - first name, last name, birth date, employed, occupation, insert by, insert at, update by, update at");
                }

                printEmployeeInfo(records.get(i));
            }
        }
    }

    public static void printEmployeeInfo(EmployeeMybatisEntity employee) {

        if (employee != null) {

            String lastName = null;
            if (employee.getLastName() != null) {
                lastName = employee.getLastName().getName();
            }

            String msg = String.format("%s - %s, %s, %s, %s, %s, %s, %s, %s, %s",
                    employee.getId(),
                    employee.getFirstName(),
                    lastName,
                    employee.getBirthDate(),
                    employee.getEmployed(),
                    employee.getOccupation(),
                    employee.getInsertBy(),
                    employee.getInsertTime(),
                    employee.getUpdateBy(),
                    employee.getUpdateTime());
            logger.info(msg);
        }
    }

}