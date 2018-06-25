package com.pphh.demo.util;

import com.pphh.demo.po.EmployeeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/25/2018
 */
public class DemoUtils {

    static Logger logger = LoggerFactory.getLogger(DemoUtils.class);

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
