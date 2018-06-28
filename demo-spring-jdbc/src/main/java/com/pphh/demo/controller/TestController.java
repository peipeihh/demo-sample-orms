package com.pphh.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/28/2018
 */
@RestController
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/test")
    public String test() {
        String sql = "select count(*) from employee";
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        return "spring jdbc dao test is completed, please check output log. count = " + count;
    }

}
