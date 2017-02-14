package com.dakor.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * .
 *
 * @author dkor
 */
@Configuration
@EnableAutoConfiguration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);

		auth.userDetailsService(userDetailsServiceBean()).and().inMemoryAuthentication().withUser("sa").password("sa");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);

		// @formatter:off
		http.authorizeRequests().anyRequest().authenticated()
				.and().formLogin().loginPage("/login").successForwardUrl("/app").permitAll()
				.and().logout().logoutUrl("/login?logout").permitAll();
		// @formatter:on
	}
}
