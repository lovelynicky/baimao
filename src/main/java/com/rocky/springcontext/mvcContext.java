package com.rocky.springcontext;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by liluoqi on 16/6/12.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.rocky.controller")
public class mvcContext {

}
