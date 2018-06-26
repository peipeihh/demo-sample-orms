package com.pphh.demo.po;

import java.sql.Date;

/**
 * EmployeeEntity
 *
 * @author huangyinhuang
 * @date 6/25/2018
 */
public class EmployeeEntity {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String employed;
    private String occupation;
    private String insertBy;
    private String updateBy;
    private Date insertTime;
    private Date updateTime;
    private Boolean isActive;

    public static EmployeeEntity createOne() {
        EmployeeEntity newEmployee = new EmployeeEntity();
        newEmployee.setFirstName("first_name_" + System.currentTimeMillis());
        newEmployee.setLastName("test_last_name");
        newEmployee.setBirthDate(new Date(System.currentTimeMillis()));
        newEmployee.setEmployed("yes");
        newEmployee.setOccupation("unknown");
        newEmployee.setInsertBy("unknown");
        newEmployee.setUpdateBy("unknown");
        newEmployee.setIsActive(Boolean.TRUE);
        return newEmployee;
    }

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

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

}
