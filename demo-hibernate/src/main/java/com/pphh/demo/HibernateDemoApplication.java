package com.pphh.demo;

import com.pphh.demo.dao.EmployeeHibernateDao;
import com.pphh.demo.po.EmployeeEntity;
import com.pphh.demo.po.EmployeeHibernateEntity;
import com.pphh.demo.util.DemoUtils;
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
public class HibernateDemoApplication {

    static Logger logger = LoggerFactory.getLogger(HibernateDemoApplication.class);

    public static void main(String[] args) {
        logger.info("start...");
        EmployeeHibernateDao employeeDao = new EmployeeHibernateDao();
        DaoTester daoTester = DaoTester.getInstance();
        daoTester.run(employeeDao);
        logger.info("the end.");
    }

}
