
## 项目介绍

本项目演示了业界中较为流行使用的各大ORM（数据对象关系映射）/POJO Data Persistence（简单Java数据对象持久化）框架的基本使用方法，包括数据模型的创建、开发DAO实现各种数据访问操作（增删改查和分页）等，以便了解它们的技术实现方法。

- 注1：ORM是Object Relation Map英文缩写，数据对象关系映射框架
- 注2：POJO是Plain Ordinary Java Object，简单Java数据对象，其数据保存到数据库的过程为Data Persistence，即数据持久化

演示的ORM/POJO Data Persistence框架包括，

| 框架 | 简介 | 一些主要DAO类库 |
| :---: | :---  | :---: |
| MyBatis 3 | 一款优秀的持久层框架，它支持定制化SQL、存储过程以及高级映射，其主要特点是使用简单的XML或注解来表达POJO和数据库表之间的映射关系。| org.apache.ibatis.session.SqlSession <br/> org.apache.ibatis.session.SqlSessionFactory  <br/> org.mybatis.dynamic.sql.SqlTable <br/> org.mybatis.dynamic.sql.SqlBuilder.*|
| Hibernate | 一个对象关系映射框架，对JDBC进行了轻量级的封装，提供sessionFactory/session实现POJO和数据库表之间的数据访问和存储。支持JPA，提供自定义的查询语言HQL。 | org.hibernate.Session <br/> org.hibernate.SessionFactory |
| Ctrip Dal | 携程数据访问类库，其提供代码生成工具，能够从数据库自动生成POJO和DAO，并提供sqlbuilder，简化开发者的代码开发过程。 | com.ctrip.platform.dal.dao.DalQueryDao <br/> com.ctrip.platform.dal.dao.DalTableDao |
| JooQ | 一种类型安全，提供面向Sql、更自然编写Sql语句的数据库访问框架，其主要包括代码生成、SQL语句构建、SQL语句执行和结果集处理这三大部分。| org.jooq.DSLContext <br/> org.jooq.Record <br/> org.jooq.Result <br/>  org.jooq.impl.DSL.* |
| Spring Boot JDBC <br/> (spring-boot-starter-jdbc)| 对JDBC的轻量级封装，提供JdbcTemplate，通过sql语句进行各种数据访问操作，并提供数据的各种映射处理。| org.springframework.jdbc.core.JdbcTemplate |
| Spring Boot JPA <br/> (spring-boot-starter-data-jpa)| 基于JPA规范的ORM实现类库 | org.springframework.data.jpa.repository.JpaRepository <br/> org.springframework.data.repository.CrudRepository |
| Spring Boot JooQ <br/>  (spring-boot-starter-jooq) | 实现了spring boot自动装配JooQ DslContext组件 | 同JooQ|
| Spring Boot JDBC + JooQ <br/>   (spring-boot-starter-jdbc + JooQ)  | 通过JooQ构建Sql语句，然后spring jdbc的JDBCTemplate执行Sql语句 | 同Spring JDBC + JooQ |

## 演示目标

对于所有的框架，逐一实现如下的DAO访问操作，并进行统一的测试流程，

``` java
package com.pphh.demo.dao;

import com.pphh.demo.po.EmployeeEntity;

import java.util.List;

/**
 * EmployeeDao
 *
 * @author huangyinhuang
 * @date 6/25/2018
 */
public interface EmployeeDao {

    /**
     * select employee by primary key id
     *
     * @param id
     * @return employee
     */
    public EmployeeEntity selectById(long id);

    /**
     * select all employees
     *
     * @return a list of employee
     */
    public List<EmployeeEntity> selectAll();

    /**
     * @return the last employee entity order by id, which has highest id value
     */
    public EmployeeEntity selectLast();

    /**
     * query the total count of employees
     *
     * @return total count of employee
     */
    public long count();

    /**
     * insert a employee entity into db
     *
     * @param employee a employee entity to be inserted
     * @return the id of new employee entity, if failed then return -1
     */
    public long insert(EmployeeEntity employee);

    /**
     * update an employee entity
     *
     * @param employee a employee entity to be updated
     * @return true if success, otherwise false
     */
    public boolean update(EmployeeEntity employee);

    /**
     * remove an employee from db
     *
     * @param employee a employee entity to be removed
     * @return true if success, otherwise false
     */
    public boolean delete(EmployeeEntity employee);

    /**
     * remove an employee entity by primary key id
     *
     * @param id id of the employee entity to be removed
     * @return true if success, otherwise false
     */
    public boolean deleteById(long id);

}

```

统一的测试操作类见，
- common-orm/src/main/java/com/pphh/demo/DaoTester

查询或存储的数据模型为，

``` java
public class EmployeeEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String employed;
    private String occupation;
    private String insertBy;
    private String updateBy;
    private Timestamp insertTime;
    private Timestamp updateTime;
    private Boolean isActive;
}
```

相应的数据库表定义为，（完整的数据库建表脚本见common-orm/src/main/resources/simple-orm.sql）

``` sql
CREATE TABLE IF NOT EXISTS `simple_orm`.`employee` (
  `id`          BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `first_name`  VARCHAR(30) NOT NULL,
  `last_name`   VARCHAR(30) NOT NULL,
  `birth_date`  DATE        NOT NULL,
  `employed`    VARCHAR(3)  NOT NULL,
  `occupation`  VARCHAR(30) NULL,
  `is_active`   TINYINT(1)  NOT NULL DEFAULT '1',
  `insert_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_by`   VARCHAR(64)          DEFAULT 'unknown',
  `update_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by`   VARCHAR(64)          DEFAULT 'unknown',
  PRIMARY KEY (id),
  INDEX `idx_firstname` (`first_name`),
  INDEX `idx_lastname` (`last_name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = 'employee';
```

## 项目编译
整个项目目录结构如下，

```
- README 使用说明文件
- pom.xml 整个Maven项目的pom文件，这是演示项目的根目录

+ common-orm 统一的DAO、PO类接口，根据各个框架一一来实现，并统一测试DaoTester
  + src/main/java
    + com/pphh/demo
      + dao/EmployeeDao 数据访问类
      + po/EmployeeEntity Java简单数据对象，和数据库表employee有对应关系
      - DaoTester 统一测试类
  + src/main/resources
    - simple-orm.sql Mysql数据库建表脚本

+ demo-mybatis-v3
  + src/main/java
      - com/pphh/demo/xml/dao/EmployeeMybatisDao 简单的MyBatis DAO实现，使用xml配置数据映射关系
      - com/pphh/demo/dynamic/dao/EmployeeMybatisDynamicDao 一个MyBatis DAO实现，使用dynamic sql配置数据映射关系
+ demo-hibernate
  + src/main/java
      - com/pphh/demo/dao/EmployeeHibernateDao 一个Hibernate DAO实现，通过xml定义数据映射关系
+ demo-ctrip-dal
  + src/main/java
      - com/pphh/demo/dao/EmployeeCtripDao 一个Ctrip DAO实现，通过JPA+自定义注解定义数据映射关系
+ demo-spring-boot-jdbc
  + src/main/java
      - com/pphh/demo/dao/EmployeeJdbcDao 一个Spring JDBC DAO实现，通过RowMapper实现数据对象转换
+ demo-spring-boot-jpa
  + src/main/java
      - com/pphh/demo/service/EmployeeSpringJpaDao 一个Spring JPA DAO实现，通过JPA注解定义数据映射关系   
+ demo-spring-boot-jooq
  + src/main/java
      - com/pphh/demo/service/EmployeeJooqDao 一个Spring JooQ DAO实现，通过JooQ代码生成数据映射关系
+ demo-spring-boot-jdbc-jooq
  + src/main/java
      - com/pphh/demo/service/EmployeeJdbcJooqDao 一个Spring JDBC + JooQ DAO实现，通过JooQ获取Sql语句+JdbcTemplate执行语句
```

请打开shell窗口，切换在演示项目的根目录中，执行如下命令，对项目编译打包，
``` bash
mvn clean compile package
```

## 演示环境

环境：JDK 8 + Maven 3.3.9 + Windows 7 SP1

注：JooQ 3.11.0版本在使用JDK 9生成数据库访问代码时有报错，请使用JDK8版本进行代码生成。

## 演示

1. 数据库准备
   - 启动Mysql数据库，然后执行common-orm/src/main/resources/simple-orm.sql脚本，创建数据库表和数据

2. 对项目进行构建打包
   - 构建命令 mvn clean compile package

3. 演示1 - MyBatis 3
   - 进入子项目demo-mybatis-v3中
   - 测试主程序入口为 com.pphh.demo.MybatisDemoApplication.main()
   - 若已构建打包，则可以执行如下的命令
   ``` bash
   java -jar ./demo-mybatis-v3/target/demo-mybatis-v3-1.0.jar
   ```
   - 若执行成功，将在控制台看到如下测试输出信息（为了方便展现，下面的信息有省略）
   ```
   20:23:11.613 [main] INFO com.pphh.demo.MybatisDemoApplication - start...
   20:23:11.616 [main] INFO com.pphh.demo.MybatisDemoApplication - test the mybatis dao with xml configuration...
   20:23:11.788 [main] INFO com.pphh.demo.DaoTester - 1) select employee by id = 1
   20:23:12.061 [main] INFO com.pphh.demo.util.DemoUtils - 1 - test, test_last_name, 2018-06-28, No, unknown, unknown, 2018-06-28 11:28:20.0, unknown, 2018-06-28 16:04:04.0
   20:23:12.061 [main] INFO com.pphh.demo.DaoTester - 2) update the employee (id = 1) by setting new birth date
   20:23:12.084 [main] INFO com.pphh.demo.DaoTester - 3) query the total count of employees
   20:23:12.089 [main] INFO com.pphh.demo.DaoTester - 4) add a new employee entity
   20:23:12.104 [main] INFO com.pphh.demo.DaoTester - 5) print the whole list of employees
   20:23:12.185 [main] INFO com.pphh.demo.DaoTester - 6) remove the employee created at previous step.
   20:23:12.189 [main] INFO com.pphh.demo.DaoTester - 7) add a employee and then remove it by latest record query.
   20:23:12.206 [main] INFO com.pphh.demo.DaoTester - expected latestEmployeeId: 97
   20:23:12.206 [main] INFO com.pphh.demo.DaoTester - actual latestEmployeeId: 97
   20:23:12.206 [main] INFO com.pphh.demo.MybatisDemoApplication - the end.
   ```

4. 演示2 - Hibernate
   - 进入子项目demo-hibernate中
   - 测试主程序入口为 com.pphh.demo.HibernateDemoApplication.main()
   - 若已构建打包，则可以执行如下的命令
   ``` bash
   java -jar ./demo-hibernate/target/demo-hibernate-1.0.jar
   ```
   - 若执行成功，将在控制台看到和演示1类似的测试输出信息。

5. 演示3 - Ctrip Dal
   - 进入子项目demo-ctrip-dal中
   - 测试主程序入口为 com.pphh.demo.CtripDaoDemoApplication.main()
   - 若已构建打包，则可以执行如下的命令 （注：Issue - 该演示命令有问题，待解决）
   ``` bash
   java -jar ./demo-ctrip-dal/target/demo-ctrip-dal-1.0.jar
   ```
   - 若执行成功，将在控制台看到和演示1类似的测试输出信息。

6. 演示4 - Spring Boot JDBC
   - 进入子项目demo-spring-boot-jdbc中，这是一个spring boot web starter项目
   - 测试主程序入口为 com.pphh.demo.SpringJdbcDemoApplication
   - 若已构建打包，则可以执行如下的命令，启动spring web应用
   ``` bash
   java -jar ./demo-spring-boot-jdbc/target/demo-spring-boot-jdbc-1.0.jar
   ```
   - 若启动成功，则可以看到如下信息，web应用启动在端口8000
   ```
   2018-06-28 20:58:59.060  INFO 27184 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
   2018-06-28 20:58:59.101  INFO 27184 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8000 (http)
   ```
   - 打开浏览器，访问 http://localhost:8000/test
   - 这时可以在后台看到和演示1类似的测试输出信息。

7. 演示5 - Spring Boot JPA
   - 进入子项目demo-spring-boot-jpa中，这是一个spring boot web starter项目
   - 测试主程序入口为 com.pphh.demo.SpringJpaDemoApplication
   - 若已构建打包，则可以执行如下的命令，启动spring web应用
   ``` bash
   java -jar ./demo-spring-boot-jpa/target/demo-spring-boot-jpa-1.0.jar
   ```
   - 若启动成功，则可以看到如下信息，web应用启动在端口8000
   ```
   2018-06-28 20:58:59.060  INFO 27184 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
   2018-06-28 20:58:59.101  INFO 27184 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8000 (http)
   ```
   - 打开浏览器，访问 http://localhost:8000/test
   - 这时可以在后台看到和演示1类似的测试输出信息。

8. 演示6 - Spring Boot JooQ
   - 进入子项目demo-spring-boot-jooq中，这是一个spring boot web starter项目
   - 测试主程序入口为 com.pphh.demo.SpringJooqDemoApplication
   - 若已构建打包，则可以执行如下的命令，启动spring web应用
   ``` bash
   java -jar ./demo-spring-boot-jooq/target/demo-spring-boot-jooq-1.0.jar
   ```
   - 若启动成功，则可以看到如下信息，web应用启动在端口8000
   ```
   2018-06-28 20:58:59.060  INFO 27184 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
   2018-06-28 20:58:59.101  INFO 27184 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8000 (http)
   ```
   - 打开浏览器，访问 http://localhost:8000/test
   - 这时可以在后台看到和演示1类似的测试输出信息。

9. 演示7 - Spring JDBC + JooQ
   - 进入子项目demo-spring-boot-jdbc-jooq中，这是一个spring boot web starter项目
   - 测试主程序入口为 com.pphh.demo.SpringJdbcJooqDemoApplication
   - 若已构建打包，则可以执行如下的命令，启动spring web应用
   ``` bash
   java -jar ./demo-spring-boot-jdbc-jooq/target/demo-spring-boot-jdbc-jooq-1.0.jar
   ```
   - 若启动成功，则可以看到如下信息，web应用启动在端口8000
   ```
   2018-06-28 20:58:59.060  INFO 27184 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
   2018-06-28 20:58:59.101  INFO 27184 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8000 (http)
   ```
   - 打开浏览器，访问 http://localhost:8000/test
   - 这时可以在后台看到和演示1类似的测试输出信息。

## 联系 Contact
我们的邮箱地址：peipeihh@qq.com，欢迎来信联系。

## 开源许可协议 License
Apache License 2.0
