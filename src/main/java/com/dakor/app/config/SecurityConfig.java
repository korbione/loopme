package com.dakor.app.config;

import com.dakor.app.data.entity.UserRole;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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

		auth.userDetailsService(userDetailsServiceBean());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);

		// @formatter:off
		http.authorizeRequests()
				.mvcMatchers("/app/users/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.ADOPS.name())
				.mvcMatchers("/app/products/**").hasAnyRole(UserRole.PUBLISHER.name(), UserRole.ADOPS.name())
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login").defaultSuccessUrl("/app", true).permitAll()
				.and().logout().logoutSuccessUrl("/login?logout").permitAll();
		// @formatter:on
	}

	@Bean
	EvaluationContextExtension securityExtension() {
		return new SecurityEvaluationContextExtension();
	}

	public static class SecurityEvaluationContextExtension extends EvaluationContextExtensionSupport {
		@Override
		public String getExtensionId() {
			return "security";
		}

		@Override
		public SecurityExpressionRoot getRootObject() {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			return new SecurityExpressionRoot(authentication) {
			};
		}
	}
}
