package com.pphh.demo.po;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * LastNameTypeHandler
 *
 * @author huangyinhuang
 * @date 6/15/2018
 */
public class LastNameTypeHandler implements TypeHandler<LastName> {

    @Override
    public void setParameter(PreparedStatement ps, int i, LastName parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter == null ? null : parameter.getName());
    }

    @Override
    public LastName getResult(ResultSet rs, String columnName) throws SQLException {
        return toLastName(rs.getString(columnName));
    }

    @Override
    public LastName getResult(ResultSet rs, int columnIndex) throws SQLException {
        return toLastName(rs.getString(columnIndex));
    }

    @Override
    public LastName getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toLastName(cs.getString(columnIndex));
    }

    private LastName toLastName(String s) {
        return s == null ? null : LastName.of(s);
    }
}
