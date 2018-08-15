package com.pphh.demo.dao;

import com.pphh.demo.jooq.db.mysql.tables.Employee;
import com.pphh.demo.jooq.db.mysql.tables.records.EmployeeRecord;
import com.pphh.demo.po.EmployeeEntity;
import com.pphh.demo.po.Page;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.pphh.demo.jooq.db.mysql.Tables.EMPLOYEE;

/**
 * EmployeeJooqDao
 *
 * @author huangyinhuang
 * @date 6/28/2018
 */
@Component
public class EmployeeJooqDao implements EmployeeDao {

    @Autowired
    DSLContext dsl;
    private Employee e = EMPLOYEE.as("e");

    @Override
    public EmployeeEntity selectById(long id) {
        EmployeeRecord record = dsl.selectFrom(e).where(e.ID.eq(id)).fetchOne();
        return record.into(EmployeeEntity.class);
    }

    @Override
    public List<EmployeeEntity> selectAll() {
        return dsl.select().from(e).fetch().into(EmployeeEntity.class);
    }

    @Override
    public EmployeeEntity selectLast() {
        EmployeeRecord record = dsl.selectFrom(e).orderBy(e.ID.desc()).limit(1).fetchOne();
        return record.into(EmployeeEntity.class);
    }

    @Override
    public long count() {
        Result<Record1<Integer>> result = dsl.selectCount().from(e).fetch();
        return result.getValues(0, Long.class).get(0);
    }

    @Override
    public long insert(EmployeeEntity employee) {
        long newEmployeeId = -1;
        EmployeeRecord record = dsl.newRecord(EMPLOYEE, employee);
        int rt = record.store();
        if (rt > 0) {
            newEmployeeId = record.getId();
            employee.setId(newEmployeeId);
        }
        return newEmployeeId;
    }

    @Override
    public boolean update(EmployeeEntity employee) {
        EmployeeRecord record = dsl.newRecord(EMPLOYEE, employee);
        return dsl.executeUpdate(record) > 0;
    }

    @Override
    public boolean delete(EmployeeEntity employee) {
        EmployeeRecord record = dsl.newRecord(EMPLOYEE, employee);
        return dsl.executeDelete(record) > 0;
    }

    @Override
    public boolean deleteById(long id) {
        return dsl.delete(e).where(e.ID.eq(id)).execute() > 0;
    }

    @Override
    public Page<EmployeeEntity> queryByPage(int index, int size) {
        if (index <= 0 || size <= 0) {
            return null;
        }

        long totoalElements = count();
        long totalPages = (long) Math.ceil((double) totoalElements / size);

        int from = (index - 1) * size;
        int to = index * size;
        List<EmployeeEntity> content = dsl.select().from(e).limit(from, to).fetchInto(EmployeeEntity.class);

        Page<EmployeeEntity> page = new Page<>();
        page.setContent(content);
        page.setTotoalElements(totoalElements);
        page.setIndex(index);
        page.setSize(size);
        page.setTotoalPages(totalPages);
        return page;
    }

}
