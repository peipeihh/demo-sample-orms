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
        logger.info("select employee by id");

        logger.info("select employee by id");
        EmployeeCtripDao employeeDao = new EmployeeCtripDao();
        EmployeeEntity employee = employeeDao.selectById(1L);
        DemoUtils.printEmployeeInfo(employee);

        employee.setBirthDate(new Date());
        boolean rt = employeeDao.update(employee);
        logger.info("update an employee entity: " + rt);

        long count = employeeDao.count();
        logger.info("count of employee = " + count);

        EmployeeEntity newEmployee = EmployeeEntity.createOne();
        int newEmployeeId = employeeDao.insert(newEmployee);
        logger.info("insert an employee entity: " + newEmployeeId);

        List<EmployeeEntity> employees = employeeDao.selectAll();
        DemoUtils.printEmployeeList(employees);

        rt = employeeDao.delete(newEmployee);
        logger.info("delete an employee entity: " + rt);

        logger.info("the end.");
    }
}
