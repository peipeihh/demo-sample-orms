package com.pphh.demo.dynamic.dao;

import com.pphh.demo.EmployeeConverter;
import com.pphh.demo.dao.EmployeeDao;
import com.pphh.demo.dynamic.mapper.SimpleTableAnnotatedMapper;
import com.pphh.demo.po.EmployeeEntity;
import com.pphh.demo.po.EmployeeMybatisEntity;
import com.pphh.demo.util.ConvertUtils;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ResourceBundle;

import static com.pphh.demo.dynamic.mapper.EmployeeTableDynamicSqlSupport.id;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static com.pphh.demo.dynamic.mapper.EmployeeTableDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * EmployeeMybatisDynamicDao
 *
 * @author huangyinhuang
 * @date 6/15/2018
 */
public class EmployeeMybatisDynamicDao implements EmployeeDao {
    public static final EmployeeTable EMPLOYEE_TABLE = new EmployeeTable();
    static String DATASOURCE_DRIVER = "datasource.driver-class-name";
    static String DATASOURCE_URL = "datasource.url";
    static String DATASOURCE_USER_NAME = "datasource.username";
    static String DATASOURCE_USER_PASSWORD = "datasource.password";
    static Logger logger = LoggerFactory.getLogger(EmployeeMybatisDynamicDao.class);
    private SqlSessionFactory sqlSessionFactory;

    public EmployeeMybatisDynamicDao() {
        ResourceBundle resource = ResourceBundle.getBundle("application");
        UnpooledDataSource ds = new UnpooledDataSource(
                resource.getString(DATASOURCE_DRIVER),
                resource.getString(DATASOURCE_URL),
                resource.getString(DATASOURCE_USER_NAME),
                resource.getString(DATASOURCE_USER_PASSWORD));
        Environment environment = new Environment("test", new JdbcTransactionFactory(), ds);
        Configuration config = new Configuration(environment);
        config.addMapper(SimpleTableAnnotatedMapper.class);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
    }

    @Override
    public EmployeeEntity selectById(long employeeId) {
        EmployeeEntity employee = null;

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SimpleTableAnnotatedMapper mapper = sqlSession.getMapper(SimpleTableAnnotatedMapper.class);

            List<EmployeeMybatisEntity> rows = mapper.selectByExample()
                    .where(id, isEqualTo(employeeId))
                    .or(occupation, isNull())
                    .build()
                    .execute();
            if (rows.size() > 0) {
                EmployeeMybatisEntity mybatisEntity = rows.get(0);
                employee = EmployeeConverter.convert(mybatisEntity);
            }

        } finally {
            sqlSession.close();
        }

        return employee;
    }

    @Override
    public List<EmployeeEntity> selectAll() {
        List<EmployeeEntity> employees = null;

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SimpleTableAnnotatedMapper mapper = sqlSession.getMapper(SimpleTableAnnotatedMapper.class);

            SelectStatementProvider selectStatement = select(id.as("A_ID"),
                    firstName, lastName, birthDate, employed, occupation, insertBy, insertTime, updateBy, updateTime, isActive)
                    .from(EMPLOYEE_TABLE)
//                    .where(id, isEqualTo(1L))
                    .build()
                    .render(RenderingStrategy.MYBATIS3);
            logger.info("sql statement: " + selectStatement.getSelectStatement());
            List<EmployeeMybatisEntity> rows = mapper.selectMany(selectStatement);
            employees = ConvertUtils.convert(rows, EmployeeConverter::convert);
        } finally {
            sqlSession.close();
        }

        return employees;
    }

    @Override
    public EmployeeEntity selectLast() {
        return null;
    }

    @Override
    public long count() {
        long count = 0;

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SimpleTableAnnotatedMapper mapper = sqlSession.getMapper(SimpleTableAnnotatedMapper.class);
            SelectStatementProvider selectStatement = select(SqlBuilder.count())
                    .from(EMPLOYEE_TABLE)
                    .build()
                    .render(RenderingStrategy.MYBATIS3);

            logger.info("sql statement: " + selectStatement.getSelectStatement());
            List<EmployeeMybatisEntity> rows = mapper.selectMany(selectStatement);
            count = rows.size();
        } finally {
            sqlSession.close();
        }

        return count;
    }

    @Override
    public long insert(EmployeeEntity employee) {
        return 0;
    }

    @Override
    public boolean update(EmployeeEntity employee) {
        return false;
    }

    @Override
    public boolean delete(EmployeeEntity employee) {
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }
}
