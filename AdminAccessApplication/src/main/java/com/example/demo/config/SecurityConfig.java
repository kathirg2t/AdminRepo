package com.example.demo.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserDetailsService userDetailsPrinciple;
	
	@Autowired
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		String password = bCryptPasswordEncoder.encode("admin@123");
		auth.inMemoryAuthentication().withUser("kathir").password(password).roles("ADMIN");
		auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN");
	}

	
	
//	@Bean
//	public AuthenticationProvider auth() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(userDetailsPrinciple);
//		provider.setPasswordEncoder(new BCryptPasswordEncoder());
//		return provider;
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/register", "/save_user", "/role", "/webjars/**").permitAll();
		http.authorizeRequests().antMatchers("/**").hasRole("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.formLogin().loginPage("/login").usernameParameter("user").passwordParameter("pass").successHandler(customAuthenticationSuccessHandler);
		http.exceptionHandling().accessDeniedPage("/access_denied");
		http.logout().invalidateHttpSession(true).clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login").permitAll();
		http.httpBasic();
	}
	
}
