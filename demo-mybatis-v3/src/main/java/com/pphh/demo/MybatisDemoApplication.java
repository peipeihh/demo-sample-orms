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

        logger.info("test the mybatis dao with xml configuration...");
        EmployeeMybatisDao mybatisDao = new EmployeeMybatisDao();
        daoTester.run(mybatisDao);

        //logger.info("test the mybatis dao with dynamic sql support...");
        //EmployeeMybatisDynamicDao mybatisDynamicDao = new EmployeeMybatisDynamicDao();
        //daoTester.run(mybatisDynamicDao);

        logger.info("the end.");
    }

}
