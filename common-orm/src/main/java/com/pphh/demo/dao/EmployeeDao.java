package com.pphh.demo.dao;

import com.pphh.demo.po.EmployeeEntity;

import java.util.List;

/**
 * EmployeeDao
 *
 * @author huangyinhuang
 * @date 6/25/2018
 */
public interface EmployeeDao {

    /**
     * select employee by primary key id
     *
     * @param id
     * @return employee
     */
    public EmployeeEntity selectById(long id);

    /**
     * select all employees
     *
     * @return a list of employee
     */
    public List<EmployeeEntity> selectAll();

    /**
     * @return the last employee entity order by id, which has highest id value
     */
    public EmployeeEntity selectLast();

    /**
     * query the total count of employees
     *
     * @return total count of employee
     */
    public long count();

    /**
     * insert a employee entity into db
     *
     * @param employee a employee entity to be inserted
     * @return the id of new employee entity, if failed then return -1
     */
    public long insert(EmployeeEntity employee);

    /**
     * update an employee entity
     *
     * @param employee a employee entity to be updated
     * @return true if success, otherwise false
     */
    public boolean update(EmployeeEntity employee);

    /**
     * remove an employee from db
     *
     * @param employee a employee entity to be removed
     * @return true if success, otherwise false
     */
    public boolean delete(EmployeeEntity employee);

    /**
     * remove an employee entity by primary key id
     *
     * @param id id of the employee entity to be removed
     * @return true if success, otherwise false
     */
    public boolean deleteById(long id);

}
