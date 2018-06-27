package com.pphh.demo.repo;

import com.pphh.demo.po.EmployeeJpaEntity;
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
public interface EmployeeRepository extends JpaRepository<EmployeeJpaEntity, Long>, JpaSpecificationExecutor<EmployeeJpaEntity> {

    @Query("SELECT e FROM EmployeeJpaEntity e WHERE e.isActive=true AND e.id=?1")
    EmployeeJpaEntity findOne(Long id);

    Page<EmployeeJpaEntity> findAll(Specification<EmployeeJpaEntity> specification, Pageable pageable);

    EmployeeJpaEntity findFirstByOrderByIdAsc();

    EmployeeJpaEntity findTopByOrderByIdDesc();

}
