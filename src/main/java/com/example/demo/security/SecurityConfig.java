package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	/*
	 * Authentication
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	
	/*
	 * Authorization
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/user/register","/user/login").permitAll()
			.antMatchers("/welcome").authenticated()
			.antMatchers("/admin").hasAuthority("ADMIN")
			.antMatchers("/employee").hasAuthority("EMPLOYEE")
			.antMatchers("/student").hasAuthority("STUDENT")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginProcessingUrl("/login")
			.loginPage("/user/login")
			.defaultSuccessUrl("/welcome",true)
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.and()
			.exceptionHandling()
			.accessDeniedPage("/user/access-denied")
			;
		
	}

}
