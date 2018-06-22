package com.pphh.demo;

import com.pphh.demo.dao.EmployeeDao;
import com.pphh.demo.po.EmployeeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/21/2018
 */
public class Main {
    static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        logger.info("select employee by id");
        EmployeeDao employeeDao = new EmployeeDao();
        EmployeeEntity employee = employeeDao.selectById(1L);
        printEmployeeInfo(employee);

        employee.setBirthDate(new Date());
        int rt = employeeDao.update(employee);
        logger.info("update an employee entity: " + rt);

        long count = employeeDao.count();
        logger.info("count of employee = " + count);

        EmployeeEntity newEmployee = EmployeeEntity.createOne();
        rt = employeeDao.insert(newEmployee);
        logger.info("insert an employee entity: " + rt);

        List<EmployeeEntity> employees = employeeDao.selectAll();
        printEmployeeList(employees);

        rt = employeeDao.delete(newEmployee);
        logger.info("delete an employee entity: " + rt);

        logger.info("the end.");
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
