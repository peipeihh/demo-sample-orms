package com.pphh.demo.dynamic.mapper;

import com.pphh.demo.po.EmployeeMybatisEntity;
import com.pphh.demo.po.LastNameTypeHandler;
import com.pphh.demo.po.YesNoTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

import java.util.List;

import static com.pphh.demo.dynamic.mapper.EmployeeTableDynamicSqlSupport.*;

/**
 * SimpleTableAnnotatedMapper
 *
 * @author huangyinhuang
 * @date 6/15/2018
 */
@Mapper
public interface SimpleTableAnnotatedMapper {

    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<EmployeeMybatisEntity> insertStatement);

    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("SimpleTableResult")
    EmployeeMybatisEntity selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "SimpleTableResult", value = {
            @Result(column = "A_ID", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "first_name", property = "firstName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "last_name", property = "lastName", jdbcType = JdbcType.VARCHAR, typeHandler = LastNameTypeHandler.class),
            @Result(column = "birth_date", property = "birthDate", jdbcType = JdbcType.DATE),
            @Result(column = "employed", property = "employed", jdbcType = JdbcType.VARCHAR, typeHandler = YesNoTypeHandler.class),
            @Result(column = "occupation", property = "occupation", jdbcType = JdbcType.VARCHAR),
            @Result(column = "insert_by", property = "insertBy", jdbcType = JdbcType.VARCHAR),
            @Result(column = "insert_time", property = "insertTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_by", property = "updateBy", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "is_active", property = "isActive", jdbcType = JdbcType.BOOLEAN)
    })
    List<EmployeeMybatisEntity> selectMany(SelectStatementProvider selectStatement);

    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<EmployeeMybatisEntity>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id.as("A_ID"), firstName, lastName, birthDate, employed, occupation)
                .from(EMPLOYEE_TABLE);
    }
}
