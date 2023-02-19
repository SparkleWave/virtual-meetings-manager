package ru.nsu.virtual_meetings.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
//import ru.nsu.virtual_meetings.filter.CredentialsAuthenticationFilter;
//import ru.nsu.virtual_meetings.filter.JwtAuthenticationFilter;
import ru.nsu.virtual_meetings.filter.CredentialsAuthenticationFilter;
import ru.nsu.virtual_meetings.filter.JwtAuthenticationFilter;
import ru.nsu.virtual_meetings.service.UserService;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .usersByUsernameQuery("select userLogin, userPassword, active, from user where userLogin=?")
//                .authoritiesByUsernameQuery("select u.userLogin, ur.roles from user u inner join roles ur on u.id = ur.userId where u.userLogin=?");
//
//    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select username, password, enable from user where username=?")
                .authoritiesByUsernameQuery("select u.username, ur.roles from user u inner join roles ur on u.user_id = ur.user_id where u.username=?");
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("admin")).roles("USER");
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        var corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);

        http.cors();

        http.authorizeRequests()
                .antMatchers("/registration", "/login").permitAll()
                .antMatchers("/users/**").hasRole("USER")
                .antMatchers("/meetings/**").hasRole("USER");

        http.formLogin().disable();
        http.csrf().disable();

        http.addFilter(new CredentialsAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthenticationFilter(userDetailsService()), CredentialsAuthenticationFilter.class);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}