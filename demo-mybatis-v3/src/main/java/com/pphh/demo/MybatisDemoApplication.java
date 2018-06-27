package com.pphh.demo;

import com.pphh.demo.dynamic.dao.EmployeeMybatisDynamicDao;
import com.pphh.demo.po.EmployeeEntity;
import com.pphh.demo.util.DemoUtils;
import com.pphh.demo.xml.dao.EmployeeMybatisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * MybatisDemoApplication
 *
 * @author huangyinhuang
 * @date 6/15/2018
 */
public class MybatisDemoApplication {

    static Logger logger = LoggerFactory.getLogger(MybatisDemoApplication.class);

    public static void main(String[] args) {
        logger.info("start...");
        DaoTester daoTester = DaoTester.getInstance();

        EmployeeMybatisDao employeeDao = new EmployeeMybatisDao();
        daoTester.run(employeeDao);


        logger.info("test the mybatis dynamic dao - count method...");
        EmployeeMybatisDynamicDao mybatisDao = new EmployeeMybatisDynamicDao();
        long count = mybatisDao.count();
        logger.info("count = " + count);

        logger.info("test the mybatis dynamic dao - select by id method...");
        EmployeeEntity employeeEntity = mybatisDao.selectById(1L);
        DemoUtils.printEmployeeInfo(employeeEntity);

        logger.info("test the mybatis dynamic dao - select all method...");
        List<EmployeeEntity> employees = mybatisDao.selectAll();
        DemoUtils.printEmployeeList(employees);

        logger.info("the end.");
    }

}
