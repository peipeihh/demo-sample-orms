package com.pphh.demo.dao;

import com.pphh.demo.po.EmployeeEntity;
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
public class EmployeeDao {

    static Logger logger = LoggerFactory.getLogger(EmployeeDao.class);
    private SessionFactory sessionFactory;

    public EmployeeDao() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public EmployeeEntity selectById(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        EmployeeEntity employee = null;
        try {
            tx = session.beginTransaction();
            employee = session.get(EmployeeEntity.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("A exception happens when executing hibernate session.", e.getMessage());
        } finally {
            session.close();
        }

        return employee;
    }

    public List selectAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        List employees = null;
        try {
            tx = session.beginTransaction();
            employees = session.createQuery("FROM EmployeeEntity").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("A exception happens when executing hibernate session.", e.getMessage());
        } finally {
            session.close();
        }

        return employees;
    }


    public int insert(EmployeeEntity employee) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("A exception happens when executing hibernate session.", e.getMessage());
        } finally {
            session.close();
        }

        return 0;
    }

    public int update(EmployeeEntity employee) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("A exception happens when executing hibernate session.", e.getMessage());
        } finally {
            session.close();
        }

        return 0;
    }

    public int delete(EmployeeEntity employee) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("A exception happens when executing hibernate session.", e.getMessage());
        } finally {
            session.close();
        }

        return 0;
    }

    public int deleteById(int id) {
        return 0;
    }

    public long count() {
        return 0;
    }

}
