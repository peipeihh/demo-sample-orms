package com.pphh.demo.xml.dao;

import com.pphh.demo.EmployeeConverter;
import com.pphh.demo.dao.EmployeeDao;
import com.pphh.demo.po.EmployeeEntity;
import com.pphh.demo.util.ConvertUtils;
import com.pphh.demo.xml.mapper.EmployeeMapper;
import com.pphh.demo.po.EmployeeMybatisEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * EmployeeMybatisDao
 *
 * @author huangyinhuang
 * @date 6/20/2018
 */
public class EmployeeMybatisDao implements EmployeeDao {

    static Logger logger = LoggerFactory.getLogger(EmployeeMybatisDao.class);
    private SqlSessionFactory sqlSessionFactory;

    public EmployeeMybatisDao() {
        try {
            String MYBATIS_CONFIG_XML = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(MYBATIS_CONFIG_XML);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            logger.error("初始化mybatis sql session factory失败。", e.getMessage());
        }
    }

    @Override
    public EmployeeEntity selectById(long id) {
        EmployeeEntity employee = null;

        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession(true);
            try {
                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
                EmployeeMybatisEntity mybatisEmployee = imageMapper.selectById(id);
                employee = EmployeeConverter.convert(mybatisEmployee);
            } catch (Exception e) {
                logger.error("an unexpected exception happens when executing mybatis sql session/mapper.", e.getMessage());
            } finally {
                session.close();
            }
        }

        return employee;
    }

    @Override
    public List<EmployeeEntity> selectAll() {
        List<EmployeeEntity> employees = null;

        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession(true);
            try {
                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
                List<EmployeeMybatisEntity> mybatisEmployees = imageMapper.selectAll();
                employees = ConvertUtils.convert(mybatisEmployees, EmployeeConverter::convert);
            } catch (Exception e) {
                logger.error("an unexpected exception happens when executing mybatis sql session/mapper.", e.getMessage());
            } finally {
                session.close();
            }
        }

        return employees;
    }

    @Override
    public EmployeeEntity selectLast() {
        EmployeeEntity employee = null;

        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession(true);
            try {
                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
                EmployeeMybatisEntity mybatisEntity = imageMapper.selectLast();
                employee = EmployeeConverter.convert(mybatisEntity);
            } catch (Exception e) {
                logger.error("an unexpected exception happens when executing mybatis sql session/mapper.", e.getMessage());
            } finally {
                session.close();
            }
        }

        return employee;
    }

    @Override
    public long count() {
        int total = 0;

        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession(true);
            try {
                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
                total = imageMapper.count();
            } catch (Exception e) {
                logger.error("an unexpected exception happens when executing mybatis sql session/mapper.", e.getMessage());
            } finally {
                session.close();
            }
        }

        return total;
    }

    @Override
    public long insert(EmployeeEntity employee) {
        long newEmployeeId = 0;

        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession(true);
            try {
                EmployeeMybatisEntity mybatisEntity = EmployeeConverter.convert(employee);
                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
                int rt = imageMapper.insert(mybatisEntity);
                if (rt > 0) {
                    newEmployeeId = mybatisEntity.getId();
                    employee.setId(newEmployeeId);
                }
            } catch (Exception e) {
                logger.error("an unexpected exception happens when executing mybatis sql session/mapper.", e.getMessage());
            } finally {
                session.close();
            }
        }

        return newEmployeeId;
    }

    @Override
    public boolean update(EmployeeEntity employee) {
        boolean bSuccess = false;

        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession(true);
            try {
                EmployeeMybatisEntity mybatisEntity = EmployeeConverter.convert(employee);
                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
                imageMapper.update(mybatisEntity);
                bSuccess = true;
            } catch (Exception e) {
                logger.error("an unexpected exception happens when executing mybatis sql session/mapper.", e.getMessage());
            } finally {
                session.close();
            }
        }

        return bSuccess;
    }

    @Override
    public boolean delete(EmployeeEntity employee) {
        return deleteById(employee.getId());
    }

    @Override
    public boolean deleteById(long id) {
        boolean bSuccess = false;

        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession(true);
            try {
                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
                imageMapper.delete(id);
                bSuccess = true;
            } catch (Exception e) {
                logger.error("an unexpected exception happens when executing mybatis sql session/mapper.", e.getMessage());
            } finally {
                session.close();
            }
        }

        return bSuccess;
    }

}
