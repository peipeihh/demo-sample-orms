package com.pphh.demo.po;

import com.ctrip.platform.dal.dao.annotation.Database;
import com.ctrip.platform.dal.dao.annotation.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.sql.Types;
import java.sql.Date;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/21/2018
 */
@Database(name = "simple_orm")
@Table(name = "employee")
public class EmployeeCtripEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(value = Types.BIGINT)
    private Long id;

    @Column(name = "first_name")
    @Type(value = Types.VARCHAR)
    private String firstName;

    @Column(name = "last_name")
    @Type(value = Types.VARCHAR)
    private String lastName;

    @Column(name = "birth_date")
    @Type(value = Types.TIMESTAMP)
    private Date birthDate;

    @Column(name = "employed")
    @Type(value = Types.VARCHAR)
    private String employed;

    @Column(name = "occupation")
    @Type(value = Types.VARCHAR)
    private String occupation;

    @Column(name = "insert_by")
    @Type(value = Types.VARCHAR)
    private String insertBy;

    @Column(name = "update_by")
    @Type(value = Types.VARCHAR)
    private String updateBy;

    @Column(name = "insert_time")
    @Type(value = Types.TIMESTAMP)
    private Timestamp insertTime;

    @Column(name = "update_time")
    @Type(value = Types.TIMESTAMP)
    private Timestamp updateTime;

    @Column(name = "is_active")
    @Type(value = Types.BIT)
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmployed() {
        return employed;
    }

    public void setEmployed(String employed) {
        this.employed = employed;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getInsertBy() {
        return insertBy;
    }

    public void setInsertBy(String insertBy) {
        this.insertBy = insertBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Timestamp insertTime) {
        this.insertTime = insertTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
