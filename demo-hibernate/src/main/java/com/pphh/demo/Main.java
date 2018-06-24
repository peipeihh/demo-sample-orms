package com.pphh.demo;

import com.pphh.demo.dao.EmployeeDao;
import com.pphh.demo.po.EmployeeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/24/2018
 */
public class Main {

    static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDao();

        EmployeeEntity employee = employeeDao.selectById(1L);
        printEmployeeInfo(employee);

        List employees = employeeDao.selectAll();
        printEmployeeList(employees);

        EmployeeEntity newEmployee = EmployeeEntity.createOne();
        employeeDao.insert(newEmployee);

        newEmployee.setBirthDate(new Date(System.currentTimeMillis()));
        employeeDao.update(newEmployee);

        employeeDao.delete(newEmployee);
    }

    public static void printEmployeeList(List<EmployeeEntity> employees) {
        if (employees != null) {
            for (int i = 0; i < employees.size(); i++) {
                if (i == 0) {
                    logger.info("id - first name, last name, birth date, employed, occupation, insert by, insert at, update by, update at");
                }

                printEmployeeInfo(employees.get(i));
            }
        }
    }

    public static void printEmployeeInfo(EmployeeEntity employee) {

        if (employee != null) {
            String msg = String.format("%s - %s, %s, %s, %s, %s, %s, %s, %s, %s",
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
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
