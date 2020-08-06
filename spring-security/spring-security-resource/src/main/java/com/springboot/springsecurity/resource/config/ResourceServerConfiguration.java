package com.springboot.springsecurity.resource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/6
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
        .anyRequest().authenticated();
//                .antMatchers("/").hasAuthority("SystemContent")
//                .antMatchers("/view/**").hasAuthority("SystemContentView")
//                .antMatchers("/insert/**").hasAuthority("SystemContentInsert")
//                .antMatchers("/update/**").hasAuthority("SystemContentUpdate")
//                .antMatchers("/delete/**").hasAuthority("SystemContentDelete");
    }
}
