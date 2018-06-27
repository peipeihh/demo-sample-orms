package com.pphh.demo;

import com.pphh.demo.po.EmployeeEntity;
import com.pphh.demo.po.EmployeeMybatisEntity;
import com.pphh.demo.po.LastName;
import com.pphh.demo.po.YesNoTypeHandler;
import com.pphh.demo.util.ConvertUtils;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/27/2018
 */
public class EmployeeConverter {

    public static EmployeeEntity convert(EmployeeMybatisEntity mybatisEntity) {
        EmployeeEntity employee = ConvertUtils.convert(mybatisEntity, EmployeeEntity.class);
        employee.setId(mybatisEntity.getId());
        if (mybatisEntity.getLastName() != null) {
            employee.setLastName(mybatisEntity.getLastName().getName());
        }
        if (mybatisEntity.getEmployed() != null) {
            employee.setEmployed(mybatisEntity.getEmployed() ? YesNoTypeHandler.YesType : YesNoTypeHandler.NoType);
        }
        return employee;
    }

    public static EmployeeMybatisEntity convert(EmployeeEntity employee) {
        EmployeeMybatisEntity mybatisEntity = ConvertUtils.convert(employee, EmployeeMybatisEntity.class);
        if (employee.getLastName() != null) {
            mybatisEntity.setLastName(LastName.of(employee.getLastName()));
        }
        if (employee.getEmployed() != null) {
            mybatisEntity.setEmployed(employee.getEmployed().equals(YesNoTypeHandler.YesType));
        }
        return mybatisEntity;
    }

}
