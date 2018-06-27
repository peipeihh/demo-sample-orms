package com.pphh.demo;

import com.pphh.demo.dao.EmployeeDao;
import com.pphh.demo.po.EmployeeEntity;
import com.pphh.demo.util.DemoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/26/2018
 */
public class DaoTester {
    static Logger logger = LoggerFactory.getLogger(DaoTester.class);

    private static DaoTester ourInstance = new DaoTester();

    public static DaoTester getInstance() {
        return ourInstance;
    }

    public void run(EmployeeDao employeeDao) {
        logger.info("1) select employee by id = 1");
        EmployeeEntity employee = employeeDao.selectById(1L);
        DemoUtils.printEmployeeInfo(employee);

        logger.info("2) update the employee (id = 1) by setting new birth date");
        Date newBirthDate = new Date(System.currentTimeMillis());
        employee.setBirthDate(newBirthDate);
        boolean rt = employeeDao.update(employee);
        logger.info("updated, new birth date = %s, result = %s", newBirthDate, rt);

        logger.info("3) query the total count of employees");
        long count = employeeDao.count();
        logger.info("count of employee = " + count);

        logger.info("4) add a new employee entity");
        EmployeeEntity newEmployee = EmployeeEntity.createOne();
        long newEmployeeId = employeeDao.insert(newEmployee);
        logger.info("new employee id = " + newEmployeeId);

        logger.info("5) print the whole list of employees");
        List<EmployeeEntity> employees = employeeDao.selectAll();
        DemoUtils.printEmployeeList(employees);

        logger.info("6) remove the employee created at previous step.");
        rt = employeeDao.delete(newEmployee);
        logger.info("removed: " + rt);

        logger.info("7) add a employee and then remove it by latest record query.");
        newEmployee = EmployeeEntity.createOne();
        long latestEmployeeId = employeeDao.insert(newEmployee);
        EmployeeEntity latestEmployee = employeeDao.selectLast();
        employeeDao.deleteById(latestEmployee.getId());
        if (latestEmployeeId != latestEmployee.getId()) {
            logger.error(String.format("expected latestEmployeeId %s != actual latestEmployeeId %s",
                    latestEmployeeId,
                    latestEmployee.getId()));
        } else {
            logger.info("expected latestEmployeeId: " + latestEmployeeId);
            logger.info("actual latestEmployeeId: " + latestEmployee.getId());
        }
    }

}
