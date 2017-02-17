package com.dakor.app.config;

import com.github.mxab.thymeleaf.extras.dataattribute.dialect.DataAttributeDialect;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * .
 *
 * @author dkor
 */
@Configuration
@EnableAutoConfiguration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	@ConditionalOnMissingBean
	public DataAttributeDialect dataAttributeDialect() {
		return new DataAttributeDialect();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		super.addViewControllers(registry);

		registry.addViewController("/").setViewName("redirect:app");
	}

	@Bean
	public LocaleResolver localeResolver(){
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		localeResolver.setCookieName("locale-cookie");
		localeResolver.setCookieMaxAge(3600);

		return localeResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		registry.addInterceptor(interceptor);
	}
}
