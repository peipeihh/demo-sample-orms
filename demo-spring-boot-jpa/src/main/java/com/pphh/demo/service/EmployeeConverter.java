package com.pphh.demo.service;

import com.pphh.demo.po.EmployeeEntity;
import com.pphh.demo.po.EmployeeJpaEntity;
import com.pphh.demo.util.ConvertUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/27/2018
 */
public class EmployeeConverter implements Converter<EmployeeJpaEntity, EmployeeEntity> {

    @Override
    public EmployeeEntity convert(EmployeeJpaEntity employeeJpaEntity) {
        return ConvertUtils.convert(employeeJpaEntity, EmployeeEntity.class);
    }

}
