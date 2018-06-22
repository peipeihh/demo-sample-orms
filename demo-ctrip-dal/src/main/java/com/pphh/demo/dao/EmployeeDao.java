package com.pphh.demo.dao;

import com.ctrip.platform.dal.dao.DalHints;
import com.ctrip.platform.dal.dao.DalRowMapper;
import com.ctrip.platform.dal.dao.DalTableDao;
import com.ctrip.platform.dal.dao.helper.DalDefaultJpaMapper;
import com.ctrip.platform.dal.dao.helper.DalDefaultJpaParser;
import com.ctrip.platform.dal.dao.sqlbuilder.SelectSqlBuilder;
import com.pphh.demo.po.EmployeeEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/21/2018
 */
public class EmployeeDao extends BaseDao {

    private DalTableDao<EmployeeEntity> client;
    private DalRowMapper<EmployeeEntity> employeeRowMapper = null;

    public EmployeeDao() throws SQLException {
        client = new DalTableDao<>(new DalDefaultJpaParser<>(EmployeeEntity.class));
        employeeRowMapper = new DalDefaultJpaMapper<>(EmployeeEntity.class);
    }

    public EmployeeEntity selectById(long id) throws SQLException {
        DalHints hints = DalHints.createIfAbsent(null);
        return client.queryByPk(id, hints);
    }

    public List<EmployeeEntity> selectAll() throws SQLException {
        SelectSqlBuilder selectCount = new SelectSqlBuilder().selectAll();
        return client.query(selectCount, DalHints.createIfAbsent(null));
    }

    public int insert(EmployeeEntity employee) throws SQLException {
        DalHints hints = DalHints.createIfAbsent(null);
        return client.insert(hints, employee);
    }

    public int update(EmployeeEntity employee) throws SQLException {
        DalHints hints = DalHints.createIfAbsent(null);
        return client.update(hints, employee);
    }

    public int delete(EmployeeEntity employee) throws SQLException {
        DalHints hints = DalHints.createIfAbsent(null);
        return client.delete(hints, employee);
    }

    public int deleteById(int id) throws SQLException {
        DalHints hints = DalHints.createIfAbsent(null);
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(id);
        return client.delete(hints, employee);
    }

    public long count() throws SQLException {
        SelectSqlBuilder selectCount = new SelectSqlBuilder().selectCount();
        Number number = client.count(selectCount, DalHints.createIfAbsent(null));
        return number.longValue();
    }

}
