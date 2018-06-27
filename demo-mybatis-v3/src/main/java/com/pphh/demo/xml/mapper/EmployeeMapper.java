package com.pphh.demo.xml.mapper;

import com.pphh.demo.po.EmployeeMybatisEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * EmployeeMapper
 *
 * @author huangyinhuang
 * @date 6/20/2018
 */
public interface EmployeeMapper {

    EmployeeMybatisEntity selectById(@Param("id") long id);

    List<EmployeeMybatisEntity> selectAll();

    EmployeeMybatisEntity selectLast();

    int insert(EmployeeMybatisEntity employee);

    void update(EmployeeMybatisEntity employee);

    void delete(@Param("id") long id);

    int count();

}
