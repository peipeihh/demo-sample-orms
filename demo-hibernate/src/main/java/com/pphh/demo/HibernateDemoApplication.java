package com.pphh.demo;

import com.pphh.demo.dao.EmployeeHibernateDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        DaoTester daoTester = DaoTester.getInstance();

        logger.info("test the hibernate dao...");
        EmployeeHibernateDao employeeDao = new EmployeeHibernateDao();
        daoTester.run(employeeDao);

        logger.info("the end.");
    }

}
