package com.pphh.demo.dao;

import com.pphh.demo.po.EmployeeEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/28/2018
 */
public class EmployeeRowMapper implements RowMapper<EmployeeEntity> {

    @Override
    public EmployeeEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(resultSet.getLong("id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setBirthDate(resultSet.getDate("birth_date"));
        employee.setEmployed(resultSet.getString("employed"));
        employee.setOccupation(resultSet.getString("occupation"));
        employee.setIsActive(resultSet.getBoolean("is_active"));
        employee.setInsertTime(resultSet.getTimestamp("insert_time"));
        employee.setInsertBy(resultSet.getString("insert_by"));
        employee.setUpdateTime(resultSet.getTimestamp("update_time"));
        employee.setUpdateBy(resultSet.getString("update_by"));
        return employee;
    }

}
