package com.pphh.demo.dao;

import com.pphh.demo.po.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/24/2018
 */
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query("SELECT e FROM EmployeeEntity e WHERE e.isActive=true AND e.id=?1")
    EmployeeEntity findOne(Long id);


}
