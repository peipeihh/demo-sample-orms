package com.pphh.demo.dao;

import static com.pphh.demo.jooq.db.mysql.Tables.EMPLOYEE;

import com.pphh.demo.jooq.db.mysql.tables.Employee;
import com.pphh.demo.jooq.db.mysql.tables.records.EmployeeRecord;
import com.pphh.demo.mapper.EmployeePreStatementCreator;
import com.pphh.demo.mapper.EmployeeRowMapper;
import com.pphh.demo.po.EmployeeEntity;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/28/2018
 */
@Component
public class EmployeeJdbcJooqDao implements EmployeeDao {

    @Autowired
    private DSLContext dsl;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Employee e = EMPLOYEE.as("e");

    @Override
    public EmployeeEntity selectById(long id) {
        //String sql = "select * from employee where id=" + id;
        SelectConditionStep<Record> query = dsl.select().from(e).where(e.ID.eq(id));
        return jdbcTemplate.queryForObject(query.getSQL(), query.getBindValues().toArray(), new EmployeeRowMapper());
    }

    @Override
    public List<EmployeeEntity> selectAll() {
        //String sql = "select * from employee";
        String sql = dsl.select().from(e).getSQL();
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    @Override
    public EmployeeEntity selectLast() {
        //String sql = "select * from employee order by id desc limit 1";
        SelectLimitPercentStep<Record> query = dsl.select().from(e).orderBy(e.ID.desc()).limit(1);
        return jdbcTemplate.queryForObject(query.getSQL(), query.getBindValues().toArray(), new EmployeeRowMapper());
    }

    @Override
    public long count() {
        //String sql = "select count(*) from employee";
        String sql = dsl.selectCount().from(e).getSQL();
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public boolean insertEx(EmployeeEntity employee) {
//        String sql = EmployeePreStatementCreator.getCreatorSql();
//        long rt = jdbcTemplate.update(sql,...);
        String sql = dsl.insertInto(e)
                .set(e.FIRST_NAME, employee.getFirstName())
                .set(e.LAST_NAME, employee.getLastName())
                .set(e.BIRTH_DATE, employee.getBirthDate())
                .set(e.EMPLOYED, employee.getEmployed())
                .set(e.OCCUPATION, employee.getOccupation())
                //.set(e.IS_ACTIVE, employee.getIsActive())
                .set(e.INSERT_TIME, employee.getInsertTime())
                .set(e.INSERT_BY, employee.getInsertBy())
                .set(e.UPDATE_TIME, employee.getUpdateTime())
                .set(e.UPDATE_BY, employee.getUpdateBy())
                .getSQL();
        return jdbcTemplate.update(sql) > 0;
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
        //String sql = EmployeePreStatementCreator.getUpdateSql(employee.getId());
        //long rt = jdbcTemplate.update(sql,...);
        UpdateConditionStep query = dsl.update(e)
                .set(e.FIRST_NAME, employee.getFirstName())
                .set(e.LAST_NAME, employee.getLastName())
                .set(e.BIRTH_DATE, employee.getBirthDate())
                .set(e.EMPLOYED, employee.getEmployed())
                .set(e.OCCUPATION, employee.getOccupation())
                //.set(e.IS_ACTIVE, employee.getIsActive())
                .set(e.INSERT_TIME, employee.getInsertTime())
                .set(e.INSERT_BY, employee.getInsertBy())
                .set(e.UPDATE_TIME, employee.getUpdateTime())
                .set(e.UPDATE_BY, employee.getUpdateBy())
                .where(e.ID.equal(employee.getId()));
        return jdbcTemplate.update(query.getSQL(), query.getBindValues().toArray()) > 0;
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
        //String sql = "delete from employee where id = ?";
        String sql = dsl.delete(e).where(e.ID.eq(id)).getSQL();
        DeleteConditionStep<EmployeeRecord> query = dsl.delete(e).where(e.ID.eq(id));
        int rt = jdbcTemplate.update(query.getSQL(), query.getBindValues().toArray());
        return rt > 0;
    }

}
