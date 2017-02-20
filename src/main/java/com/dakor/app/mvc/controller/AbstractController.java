package com.dakor.app.mvc.controller;

import com.dakor.app.service.AppContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * .
 *
 * @author dkor
 */
abstract class AbstractController {
	private AppContext appContext;

	@Autowired
	public void setAppContext(AppContext appContext) {
		this.appContext = appContext;
	}

	AppContext getAppContext() {
		return appContext;
	}
}
