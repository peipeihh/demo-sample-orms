package com.pphh.demo;

import com.pphh.demo.dao.EmployeeCtripDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

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
        DaoTester daoTester = DaoTester.getInstance();

        logger.info("test the ctrip dao...");
        EmployeeCtripDao employeeDao = new EmployeeCtripDao();
        daoTester.run(employeeDao);

        logger.info("the end.");
    }
}
