package com.pphh.demo.dao;

import com.ctrip.platform.dal.dao.DalHints;
import com.ctrip.platform.dal.dao.DalRowMapper;
import com.ctrip.platform.dal.dao.DalTableDao;
import com.ctrip.platform.dal.dao.StatementParameters;
import com.ctrip.platform.dal.dao.helper.DalDefaultJpaMapper;
import com.ctrip.platform.dal.dao.helper.DalDefaultJpaParser;
import com.ctrip.platform.dal.dao.sqlbuilder.FreeSelectSqlBuilder;
import com.ctrip.platform.dal.dao.sqlbuilder.FreeUpdateSqlBuilder;
import com.ctrip.platform.dal.dao.sqlbuilder.SelectSqlBuilder;
import com.pphh.demo.po.EmployeeCtripEntity;
import com.pphh.demo.po.EmployeeEntity;
import com.pphh.demo.util.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/21/2018
 */
public class EmployeeCtripDao extends CtripAbstractDao implements EmployeeDao {

    static Logger logger = LoggerFactory.getLogger(EmployeeCtripDao.class);

    private DalTableDao<EmployeeCtripEntity> client;
    private DalRowMapper<EmployeeCtripEntity> employeeRowMapper = null;

    public EmployeeCtripDao() throws SQLException {
        client = new DalTableDao<>(new DalDefaultJpaParser<>(EmployeeCtripEntity.class));
        employeeRowMapper = new DalDefaultJpaMapper<>(EmployeeCtripEntity.class);
    }

    @Override
    public EmployeeEntity selectById(long id) {
        EmployeeEntity employeeEntity = null;
        try {
            DalHints hints = DalHints.createIfAbsent(null);
            EmployeeCtripEntity employeeCtripEntity = client.queryByPk(id, hints);
            employeeEntity = ConvertUtils.convert(employeeCtripEntity, EmployeeEntity.class);
        } catch (SQLException e) {
            logger.error("an unexpected exception happens when using ctrip dao client.", e.getMessage());
        }
        return employeeEntity;
    }

    @Override
    public List<EmployeeEntity> selectAll() {
        SelectSqlBuilder selectCount = new SelectSqlBuilder().selectAll();
        List<EmployeeEntity> employeeList = null;
        try {
            List<EmployeeCtripEntity> employeeCtripList = client.query(selectCount, DalHints.createIfAbsent(null));
            employeeList = ConvertUtils.convert(employeeCtripList, EmployeeEntity.class);
        } catch (SQLException e) {
            logger.error("an unexpected exception happens when using ctrip dao client.", e.getMessage());
        }
        return employeeList;
    }

    @Override
    public EmployeeEntity selectLast() {
        SelectSqlBuilder selectCount = new SelectSqlBuilder().select();
        EmployeeEntity employeeEntity = null;
        try {
            FreeSelectSqlBuilder query = new FreeSelectSqlBuilder<EmployeeCtripEntity>();
            StatementParameters parameters = new StatementParameters();
            query.with(parameters);
            query.selectAll().from("employee").orderBy("id", false);
            query.mapWith(EmployeeCtripEntity.class);

            List l = (List) queryDao.query(query, parameters, DalHints.createIfAbsent(null));
            if (l.size() > 0) {
                //EmployeeCtripEntity employeeCtripEntity = (EmployeeCtripEntity) l.get(0);
                //employeeEntity = ConvertUtils.convert(employeeCtripEntity, EmployeeEntity.class);
                Long lastEmployeeId = (Long) l.get(0);
                employeeEntity = this.selectById(lastEmployeeId);
            }
        } catch (SQLException e) {
            logger.error("an unexpected exception happens when using ctrip dao client.", e.getMessage());
        }
        return employeeEntity;
    }

    @Override
    public long count() {
        long rt = 0;
        try {
            SelectSqlBuilder selectCount = new SelectSqlBuilder().selectCount();
            Number number = client.count(selectCount, DalHints.createIfAbsent(null));
            rt = number.longValue();
        } catch (SQLException e) {
            logger.error("an unexpected exception happens when using ctrip dao client.", e.getMessage());
        }
        return rt;
    }

    @Override
    public long insert(EmployeeEntity employee) {
        int rt = 0;
        try {
            EmployeeCtripEntity employeeCtripEntity = ConvertUtils.convert(employee, EmployeeCtripEntity.class);
            DalHints hints = DalHints.createIfAbsent(null);
            rt = client.insert(hints, employeeCtripEntity);
            employee.setId(employeeCtripEntity.getId());
        } catch (SQLException e) {
            logger.error("an unexpected exception happens when using ctrip dao client.", e.getMessage());
        }
        return rt;
    }

    @Override
    public boolean update(EmployeeEntity employee) {
        boolean rt = false;
        try {
            EmployeeCtripEntity employeeCtripEntity = ConvertUtils.convert(employee, EmployeeCtripEntity.class);
            DalHints hints = DalHints.createIfAbsent(null);
            client.update(hints, employeeCtripEntity);
            rt = true;
        } catch (SQLException e) {
            logger.error("an unexpected exception happens when using ctrip dao client.", e.getMessage());
        }
        return rt;
    }

    @Override
    public boolean delete(EmployeeEntity employee) {
        boolean rt = false;
        try {
            EmployeeCtripEntity employeeCtripEntity = ConvertUtils.convert(employee, EmployeeCtripEntity.class);
            DalHints hints = DalHints.createIfAbsent(null);
            client.delete(hints, employeeCtripEntity);
            rt = true;
        } catch (SQLException e) {
            logger.error("an unexpected exception happens when using ctrip dao client.", e.getMessage());
        }
        return rt;
    }

    @Override
    public boolean deleteById(long id) {
        boolean rt = false;
        try {
            DalHints hints = DalHints.createIfAbsent(null);
            EmployeeCtripEntity employee = new EmployeeCtripEntity();
            employee.setId(id);
            client.delete(hints, employee);
            rt = true;
        } catch (SQLException e) {
            logger.error("an unexpected exception happens when using ctrip dao client.", e.getMessage());
        }
        return rt;
    }


}
