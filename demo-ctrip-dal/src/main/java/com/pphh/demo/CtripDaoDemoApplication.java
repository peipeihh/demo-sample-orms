package com.pphh.demo;

import com.pphh.demo.dao.EmployeeCtripDao;
import com.pphh.demo.po.EmployeeEntity;
import com.pphh.demo.util.DemoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * This is demo application for Ctrip Dao/Orm
 *
 * @author huangyinhuang
 * @date 6/21/2018
 */
public class CtripDaoDemoApplication {
    static Logger logger = LoggerFactory.getLogger(CtripDaoDemoApplication.class);

    public static void main(String[] args) throws SQLException {
        logger.info("start...");

        logger.info("1) select employee by id = 1");
        EmployeeCtripDao employeeDao = new EmployeeCtripDao();
        EmployeeEntity employee = employeeDao.selectById(1L);
        DemoUtils.printEmployeeInfo(employee);

        logger.info("2) update the employee (id = 1) by setting new birth date");
        Date newBirthDate = new Date();
        employee.setBirthDate(newBirthDate);
        boolean rt = employeeDao.update(employee);
        logger.info("updated, new birth date = %s, result = %s", newBirthDate, rt);

        logger.info("3) query the total count of employees");
        long count = employeeDao.count();
        logger.info("count of employee = " + count);

        logger.info("4) add a new employee entity");
        EmployeeEntity newEmployee = EmployeeEntity.createOne();
        int newEmployeeId = employeeDao.insert(newEmployee);
        logger.info("new employee id = " + newEmployeeId);

        logger.info("5) print the whole list of employees");
        List<EmployeeEntity> employees = employeeDao.selectAll();
        DemoUtils.printEmployeeList(employees);

        logger.info("6) remove the employee created at previous step.");
        rt = employeeDao.delete(newEmployee);
        logger.info("removed: " + rt);

        logger.info("the end.");
    }
}
