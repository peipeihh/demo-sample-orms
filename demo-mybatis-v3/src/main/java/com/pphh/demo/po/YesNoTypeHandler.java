package com.pphh.demo.po;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * YesNoTypeHandler
 *
 * @author huangyinhuang
 * @date 6/15/2018
 */
public class YesNoTypeHandler implements TypeHandler<Boolean> {

    public static String YesType = "Yes";
    public static String NoType = "No";

    @Override
    public void setParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter ? YesType : NoType);
    }

    @Override
    public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
        return YesType.equals(rs.getString(columnName));
    }

    @Override
    public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
        return YesType.equals(rs.getString(columnIndex));
    }

    @Override
    public Boolean getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return YesType.equals(cs.getString(columnIndex));
    }
}
