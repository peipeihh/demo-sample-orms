package com.pphh.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/24/2018
 */
@Configuration
@EnableJpaRepositories("com.pphh.demo.dao")
public class JpaConfiguration {
}
