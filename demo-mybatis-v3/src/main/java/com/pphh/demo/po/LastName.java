package com.pphh.demo.po;

/**
 * LastName
 *
 * @author huangyinhuang
 * @date 6/15/2018
 */
public class LastName {
    private String name;

    public static LastName of(String name) {
        LastName lastName = new LastName();
        lastName.setName(name);
        return lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
