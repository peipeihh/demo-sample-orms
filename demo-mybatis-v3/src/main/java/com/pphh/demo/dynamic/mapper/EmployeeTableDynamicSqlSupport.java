package com.pphh.demo.dynamic.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/15/2018
 */
public final class EmployeeTableDynamicSqlSupport {

    public static final EmployeeTable EMPLOYEE_TABLE = new EmployeeTable();

    public static final SqlColumn<Long> id = EMPLOYEE_TABLE.id;
    public static final SqlColumn<String> firstName = EMPLOYEE_TABLE.firstName;
    public static final SqlColumn<String> lastName = EMPLOYEE_TABLE.lastName;
    public static final SqlColumn<Date> birthDate = EMPLOYEE_TABLE.birthDate;
    public static final SqlColumn<Boolean> employed = EMPLOYEE_TABLE.employed;
    public static final SqlColumn<String> occupation = EMPLOYEE_TABLE.occupation;
    public static final SqlColumn<String> insertBy = EMPLOYEE_TABLE.insertBy;
    public static final SqlColumn<Timestamp> insertTime = EMPLOYEE_TABLE.insertTime;
    public static final SqlColumn<String> updateBy = EMPLOYEE_TABLE.updateBy;
    public static final SqlColumn<Timestamp> updateTime = EMPLOYEE_TABLE.updateTime;
    public static final SqlColumn<Boolean> isActive = EMPLOYEE_TABLE.isActive;

    public static final class EmployeeTable extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);
        public final SqlColumn<String> firstName = column("first_name", JDBCType.VARCHAR);
        public final SqlColumn<String> lastName = column("last_name", JDBCType.VARCHAR, "com.pphh.demo.mapper.LastNameTypeHandler");
        public final SqlColumn<Date> birthDate = column("birth_date", JDBCType.DATE);
        public final SqlColumn<Boolean> employed = column("employed", JDBCType.VARCHAR, "com.pphh.demo.mapper.YesNoTypeHandler");
        public final SqlColumn<String> occupation = column("occupation", JDBCType.VARCHAR);
        public final SqlColumn<String> insertBy = column("insert_by", JDBCType.VARCHAR);
        public final SqlColumn<Timestamp> insertTime = column("insert_time", JDBCType.DATE);
        public final SqlColumn<String> updateBy = column("update_by", JDBCType.VARCHAR);
        public final SqlColumn<Timestamp> updateTime = column("update_time", JDBCType.DATE);
        public final SqlColumn<Boolean> isActive = column("is_active", JDBCType.BOOLEAN);

        public EmployeeTable() {
            super("employee");
        }
    }

}
