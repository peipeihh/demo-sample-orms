package com.pphh.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/24/2018
 */
@SpringBootApplication
public class SpringJpaDemoApplication {

    static Logger logger = LoggerFactory.getLogger(SpringJpaDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaDemoApplication.class, args);
    }
}
