package com.pphh.demo.dao;

import com.pphh.demo.mapper.EmployeePreStatementCreator;
import com.pphh.demo.mapper.EmployeeRowMapper;
import com.pphh.demo.po.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * EmployeeJdbcDao
 *
 * @author huangyinhuang
 * @date 6/28/2018
 */
@Component
public class EmployeeJdbcDao implements EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public EmployeeEntity selectById(long id) {
        String sql = "select * from employee where id=" + id;
        return jdbcTemplate.queryForObject(sql, new EmployeeRowMapper());
    }

    @Override
    public List<EmployeeEntity> selectAll() {
        String sql = "select * from employee";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    @Override
    public EmployeeEntity selectLast() {
        String sql = "select * from employee order by id desc limit 1";
        return jdbcTemplate.queryForObject(sql, new EmployeeRowMapper());
    }

    @Override
    public long count() {
        String sql = "select count(*) from employee";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public boolean insertEx(EmployeeEntity employee) {
        String sql = EmployeePreStatementCreator.getCreatorSql();
        long rt = jdbcTemplate.update(sql,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getBirthDate(),
                employee.getEmployed(),
                employee.getOccupation(),
                employee.getIsActive(),
                employee.getInsertTime(),
                employee.getInsertBy(),
                employee.getUpdateTime(),
                employee.getUpdateBy());
        return rt > 0;
    }

    @Override
    public long insert(EmployeeEntity employee) {
        long newEmployeeId = -1;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        long rt = jdbcTemplate.update(new EmployeePreStatementCreator(employee), keyHolder);
        if (rt > 0) {
            newEmployeeId = keyHolder.getKey().intValue();
            employee.setId(newEmployeeId);
        }
        return newEmployeeId;
    }

    @Override
    public boolean update(EmployeeEntity employee) {
        String sql = EmployeePreStatementCreator.getUpdateSql(employee.getId());
        long rt = jdbcTemplate.update(sql,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getBirthDate(),
                employee.getEmployed(),
                employee.getOccupation(),
                employee.getIsActive(),
                employee.getInsertTime(),
                employee.getInsertBy(),
                employee.getUpdateTime(),
                employee.getUpdateBy());
        return rt > 0;
    }

    @Override
    public boolean delete(EmployeeEntity employee) {
        boolean bSuccess = false;
        if (employee.getId() != null) {
            bSuccess = deleteById(employee.getId());
        }
        return bSuccess;
    }

    @Override
    public boolean deleteById(long id) {
        String sql = "delete from employee where id = ?";
        int rt = jdbcTemplate.update(sql, id);
        return rt > 0;
    }

}
