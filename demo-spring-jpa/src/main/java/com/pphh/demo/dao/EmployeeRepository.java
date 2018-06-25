package com.pphh.demo.dao;

import com.pphh.demo.po.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/24/2018
 */
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>, JpaSpecificationExecutor<EmployeeEntity> {

    @Query("SELECT e FROM EmployeeEntity e WHERE e.isActive=true AND e.id=?1")
    EmployeeEntity findOne(Long id);

    Page<EmployeeEntity> findAll(Specification<EmployeeEntity> specification, Pageable pageable);

}
