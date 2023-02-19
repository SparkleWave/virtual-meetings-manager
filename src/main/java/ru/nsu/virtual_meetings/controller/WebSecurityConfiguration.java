//package ru.nsu.virtual_meetings.controller;
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.POST,"/users/user").permitAll()
//                .antMatchers(HttpMethod.PUT,"/users/{userId}").permitAll()
//                .antMatchers(HttpMethod.DELETE,"/users/{userId}").permitAll()
//                .antMatchers(HttpMethod.POST,"/meetings/meeting").permitAll()
//                .antMatchers(HttpMethod.PUT,"/meetings/{meetingId}").permitAll()
//                .antMatchers(HttpMethod.GET,"/meetings/{meetingId}").permitAll()
//                .antMatchers(HttpMethod.DELETE,"/meetings/{meetingId}").permitAll()
//                .antMatchers(HttpMethod.GET,"/meetings-users/{meetingId}").permitAll()
//                .antMatchers(HttpMethod.GET,"/users-meetings/{userId}").permitAll()
//                .anyRequest().authenticated();
//    }
//}