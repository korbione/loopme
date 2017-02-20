package com.dakor.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * .
 *
 * @author dkor
 */
@Component
public class Messages {
	private MessageSource messageSource;
	private MessageSourceAccessor accessor;

	@PostConstruct
	private void init() {
		accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
	}

	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String get(String msgKey) {
		return accessor.getMessage(msgKey, LocaleContextHolder.getLocale());
	}

}
