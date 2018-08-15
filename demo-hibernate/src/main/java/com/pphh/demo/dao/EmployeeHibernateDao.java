package com.pphh.demo.dao;

import com.pphh.demo.po.EmployeeEntity;
import com.pphh.demo.po.EmployeeHibernateEntity;
import com.pphh.demo.po.Page;
import com.pphh.demo.util.ConvertUtils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
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
    public EmployeeEntity selectLast() {
        EmployeeEntity employee = null;

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List employeeHibernateList = session.createQuery("FROM EmployeeHibernateEntity ORDER BY id DESC").list();
            tx.commit();
            if (employeeHibernateList.size() > 0) {
                EmployeeHibernateEntity employeeHibernate = (EmployeeHibernateEntity) employeeHibernateList.get(0);
                employee = ConvertUtils.convert(employeeHibernate, EmployeeEntity.class);
            }
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
    public long count() {
        long count = 0;

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            count = (Long) session.createQuery("SELECT count(*) FROM EmployeeHibernateEntity").uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("A exception happens when executing hibernate sql session.", e.getMessage());
        } finally {
            session.close();
        }

        return count;
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
    public boolean deleteById(long id) {

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        boolean bSuccess = false;
        try {
            tx = session.beginTransaction();
            int result = session.createQuery("DELETE EmployeeHibernateEntity e where e.id= :id").setParameter("id", id).executeUpdate();
            tx.commit();
            bSuccess = result > 0;
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
    public Page<EmployeeEntity> queryByPage(int index, int size) {
        List<EmployeeEntity> employeeList = null;

        long totoalElements = 0;
        long totalPages = 0;
        int from = (index - 1) * size;

        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("FROM EmployeeHibernateEntity");

            ScrollableResults scroll = query.scroll();
            scroll.last();
            totoalElements = scroll.getRowNumber() + 1;
            totalPages = (long) Math.ceil((double) totoalElements / size);

            query.setFirstResult(from);
            query.setMaxResults(size);
            List employeeHibernateList = query.list();
            employeeList = ConvertUtils.convert(employeeHibernateList, EmployeeEntity.class);
        } catch (HibernateException e) {
            logger.info("A exception happens when executing hibernate sql session.", e.getMessage());
        } finally {
            session.close();
        }

        Page<EmployeeEntity> page = new Page<>();
        page.setContent(employeeList);
        page.setTotoalElements(totoalElements);
        page.setIndex(index);
        page.setSize(size);
        page.setTotoalPages(totalPages);

        return page;
    }

}
