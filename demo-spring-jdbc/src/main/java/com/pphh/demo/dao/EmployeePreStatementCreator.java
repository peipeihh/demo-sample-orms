package com.pphh.demo.dao;

import com.pphh.demo.po.EmployeeEntity;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/28/2018
 */
public class EmployeePreStatementCreator implements PreparedStatementCreator {

    private String sql = null;
    private EmployeeEntity employee = null;

    public EmployeePreStatementCreator(EmployeeEntity employee) {
        if (employee == null) {
            throw new NullPointerException("employee cannot be null");
        }
        this.sql = "insert into " +
                "employee (first_name, last_name, birth_date, employed, occupation, is_active, insert_time, insert_by, update_time, update_by)" +
                "values  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.employee = employee;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, this.employee.getFirstName());
        ps.setString(2, this.employee.getLastName());
        ps.setDate(3, this.employee.getBirthDate());
        ps.setString(4, this.employee.getEmployed());
        ps.setString(5, this.employee.getOccupation());
        ps.setBoolean(6, this.employee.getIsActive());
        ps.setTimestamp(7, this.employee.getInsertTime());
        ps.setString(8, this.employee.getInsertBy());
        ps.setTimestamp(9, this.employee.getUpdateTime());
        ps.setString(10, this.employee.getUpdateBy());
        return ps;
    }
}
