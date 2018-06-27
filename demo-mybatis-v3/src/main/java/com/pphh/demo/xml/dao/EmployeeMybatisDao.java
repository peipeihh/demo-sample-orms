package com.pphh.demo.xml.dao;

import com.pphh.demo.dao.EmployeeDao;
import com.pphh.demo.po.EmployeeEntity;
import com.pphh.demo.po.LastName;
import com.pphh.demo.po.YesNoTypeHandler;
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

    public static EmployeeEntity converter(EmployeeMybatisEntity mybatisEntity) {
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

    public static EmployeeMybatisEntity converter(EmployeeEntity employee) {
        EmployeeMybatisEntity mybatisEntity = ConvertUtils.convert(employee, EmployeeMybatisEntity.class);
        if (employee.getLastName() != null) {
            mybatisEntity.setLastName(LastName.of(employee.getLastName()));
        }
        if (employee.getEmployed() != null) {
            mybatisEntity.setEmployed(employee.getEmployed().equals(YesNoTypeHandler.YesType));
        }
        return mybatisEntity;
    }

//    public EmployeeMybatisEntity getById(long id) {
//        EmployeeMybatisEntity employee = null;
//
//        if (sqlSessionFactory != null) {
//            SqlSession session = sqlSessionFactory.openSession(true);
//            try {
//                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
//                employee = imageMapper.selectById(id);
//            } catch (Exception e) {
//                logger.error(e.getMessage(), e);
//            } finally {
//                session.close();
//            }
//        }
//
//        return employee;
//    }

//    public List<EmployeeMybatisEntity> readAll() {
//        List<EmployeeMybatisEntity> employees = null;
//
//        if (sqlSessionFactory != null) {
//            SqlSession session = sqlSessionFactory.openSession(true);
//            try {
//                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
//                employees = imageMapper.selectAll();
//            } catch (Exception e) {
//                logger.error(e.getMessage(), e);
//            } finally {
//                session.close();
//            }
//        }
//
//        return employees;
//    }

//    public boolean insert(EmployeeMybatisEntity newEmployee) {
//        boolean bSuccess = false;
//
//        if (sqlSessionFactory != null) {
//            SqlSession session = sqlSessionFactory.openSession(true);
//            try {
//                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
//                bSuccess = imageMapper.insert(newEmployee) != 0;
//            } catch (Exception e) {
//                logger.error(e.getMessage(), e);
//            } finally {
//                session.close();
//            }
//        }
//
//        return bSuccess;
//    }

//    public void update(EmployeeMybatisEntity newEmployee) {
//
//        if (sqlSessionFactory != null) {
//            SqlSession session = sqlSessionFactory.openSession(true);
//            try {
//                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
//                imageMapper.update(newEmployee);
//            } catch (Exception e) {
//                logger.error("an unexpected exception happens when executing mybatis sql session/mapper.", e.getMessage());
//            } finally {
//                session.close();
//            }
//        }
//
//    }

//    public void delete(long id) {
//
//        if (sqlSessionFactory != null) {
//            SqlSession session = sqlSessionFactory.openSession(true);
//            try {
//                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
//                imageMapper.delete(id);
//            } catch (Exception e) {
//                logger.error("an unexpected exception happens when executing mybatis sql session/mapper.", e.getMessage());
//            } finally {
//                session.close();
//            }
//        }
//
//    }

    @Override
    public EmployeeEntity selectById(long id) {
        EmployeeEntity employee = null;

        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession(true);
            try {
                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
                EmployeeMybatisEntity mybatisEmployee = imageMapper.selectById(id);
                employee = EmployeeMybatisDao.converter(mybatisEmployee);
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
                employees = ConvertUtils.convert(mybatisEmployees, EmployeeMybatisDao::converter);
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
                employee = EmployeeMybatisDao.converter(mybatisEntity);
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
                EmployeeMybatisEntity mybatisEntity = converter(employee);
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
                EmployeeMybatisEntity mybatisEntity = converter(employee);
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
