package com.pphh.demo.dao;

import com.pphh.demo.po.EmployeeEntity;
import com.pphh.demo.po.EmployeeHibernateEntity;
import com.pphh.demo.util.ConvertUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/24/2018
 */
public class EmployeeHibernateDao implements EmployeeDao {

    static Logger logger = LoggerFactory.getLogger(EmployeeHibernateDao.class);
    private SessionFactory sessionFactory;

    public EmployeeHibernateDao() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public EmployeeEntity selectById(long id) {
        EmployeeEntity employee = null;

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            EmployeeHibernateEntity employeeHibernate = session.get(EmployeeHibernateEntity.class, id);
            tx.commit();
            employee = ConvertUtils.convert(employeeHibernate, EmployeeEntity.class);
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("A exception happens when executing hibernate sql session.", e.getMessage());
        } finally {
            session.close();
        }

        return employee;
    }

    @Override
    public List<EmployeeEntity> selectAll() {
        List<EmployeeEntity> employeeList = null;

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List employeeHibernateList = session.createQuery("FROM EmployeeHibernateEntity").list();
            tx.commit();
            employeeList = ConvertUtils.convert(employeeHibernateList, EmployeeEntity.class);
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("A exception happens when executing hibernate sql session.", e.getMessage());
        } finally {
            session.close();
        }

        return employeeList;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public long insert(EmployeeEntity employee) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            EmployeeHibernateEntity employeeHibernate = ConvertUtils.convert(employee, EmployeeHibernateEntity.class);
            tx = session.beginTransaction();
            session.save(employeeHibernate);
            tx.commit();
            employee.setId(employeeHibernate.getId());
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("A exception happens when executing hibernate sql session.", e.getMessage());
        } finally {
            session.close();
        }

        return (employee == null) ? 0 : employee.getId();
    }

    @Override
    public boolean update(EmployeeEntity employee) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        boolean bSuccess = false;
        try {
            EmployeeHibernateEntity employeeHibernate = ConvertUtils.convert(employee, EmployeeHibernateEntity.class);
            tx = session.beginTransaction();
            session.update(employeeHibernate);
            tx.commit();
            bSuccess = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("A exception happens when executing hibernate sql session.", e.getMessage());
        } finally {
            session.close();
        }

        return bSuccess;
    }

    @Override
    public boolean delete(EmployeeEntity employee) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        boolean bSuccess = false;
        try {
            EmployeeHibernateEntity employeeHibernate = ConvertUtils.convert(employee, EmployeeHibernateEntity.class);
            tx = session.beginTransaction();
            session.delete(employeeHibernate);
            tx.commit();
            bSuccess = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("A exception happens when executing hibernate sql session.", e.getMessage());
        } finally {
            session.close();
        }

        return bSuccess;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

}
