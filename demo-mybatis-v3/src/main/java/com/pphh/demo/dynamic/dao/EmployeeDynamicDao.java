package com.pphh.demo.dynamic.dao;

import com.pphh.demo.dynamic.mapper.SimpleTableAnnotatedMapper;
import com.pphh.demo.po.EmployeeEntity;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ResourceBundle;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static com.pphh.demo.dynamic.mapper.EmployeeTableDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * EmployeeDynamicDao
 *
 * @author huangyinhuang
 * @date 6/15/2018
 */
public class EmployeeDynamicDao {
    public static final EmployeeTable EMPLOYEE_TABLE = new EmployeeTable();
    static String DATASOURCE_DRIVER = "datasource.driver-class-name";
    static String DATASOURCE_URL = "datasource.url";
    static String DATASOURCE_USER_NAME = "datasource.username";
    static String DATASOURCE_USER_PASSWORD = "datasource.password";
    static Logger logger = LoggerFactory.getLogger(EmployeeDynamicDao.class);
    private SqlSessionFactory sqlSessionFactory;

    public EmployeeDynamicDao() {
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

    public List<EmployeeEntity> readUserIdOne() {
        List<EmployeeEntity> rows = null;

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SimpleTableAnnotatedMapper mapper = sqlSession.getMapper(SimpleTableAnnotatedMapper.class);

            rows = mapper.selectByExample()
                    .where(id, isEqualTo(1L))
                    .or(occupation, isNull())
                    .build()
                    .execute();

        } finally {
            sqlSession.close();
        }

        return rows;

    }

    public List<EmployeeEntity> readAll() {
        List<EmployeeEntity> rows = null;

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

            rows = mapper.selectMany(selectStatement);

        } finally {
            sqlSession.close();
        }

        return rows;

    }

    public List<EmployeeEntity> readCount() {
        List<EmployeeEntity> rows = null;

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SimpleTableAnnotatedMapper mapper = sqlSession.getMapper(SimpleTableAnnotatedMapper.class);

            SelectStatementProvider selectStatement = select(count())
                    .from(EMPLOYEE_TABLE)
                    .build()
                    .render(RenderingStrategy.MYBATIS3);

            logger.info("sql statement: " + selectStatement.getSelectStatement());

            rows = mapper.selectMany(selectStatement);

        } finally {
            sqlSession.close();
        }

        return rows;

    }

}
