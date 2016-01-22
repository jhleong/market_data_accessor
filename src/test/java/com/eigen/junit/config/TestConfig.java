package com.eigen.junit.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
		basePackages = "com.eigen.config"
				+ ", com.eigen.impl"
				+ ", com.eigen.controller"
				)
public class TestConfig {

}
