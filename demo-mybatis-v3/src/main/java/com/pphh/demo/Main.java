package com.pphh.demo;


import com.pphh.demo.dynamic.dao.EmployeeDynamicDao;
//import com.pphh.demo.dynamic.po.SimpleTableRecord;
import com.pphh.demo.po.LastName;
import com.pphh.demo.xml.dao.EmployeeDao;
import com.pphh.demo.po.EmployeeEntity;
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
public class Main {

    static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("hello,world");

        EmployeeDynamicDao mybatisDao = new EmployeeDynamicDao();
        List<EmployeeEntity> records1 = mybatisDao.readCount();
        logger.info("count = " + records1.size());

        List<EmployeeEntity> records2 = mybatisDao.readUserIdOne();
        printUserRecord(records2);

        List<EmployeeEntity> records3 = mybatisDao.readAll();
        printUserRecord(records3);


        EmployeeDao employeeDao = new EmployeeDao();
        EmployeeEntity employee = employeeDao.getById(1L);
        printEmployeeInfo(employee);

        employeeDao.update(employee);

        List<EmployeeEntity> employees = employeeDao.readAll();
        printUserRecord(employees);

        EmployeeEntity newEmployee = new EmployeeEntity();
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

    public static void printUserRecord(List<EmployeeEntity> records) {
        if (records != null) {
            for (int i = 0; i < records.size(); i++) {
                if (i == 0) {
                    logger.info("id - first name, last name, birth date, employed, occupation, insert by, insert at, update by, update at");
                }

                printEmployeeInfo(records.get(i));
            }
        }
    }

    public static void printEmployeeInfo(EmployeeEntity employee) {

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
