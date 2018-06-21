package com.pphh.demo.xml.mapper;

import com.pphh.demo.po.EmployeeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * EmployeeMapper
 *
 * @author huangyinhuang
 * @date 6/20/2018
 */
public interface EmployeeMapper {

    EmployeeEntity selectById(@Param("id") long id);

    List<EmployeeEntity> selectAll();

    int insert(EmployeeEntity employee);

    void update(EmployeeEntity employee);

    void delete(@Param("id") long id);

    int count();

}
