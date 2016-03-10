package org.nasdanika.taskmanager.ui.driver.pages.impl;

import org.nasdanika.taskmanager.ui.driver.pages.TaskManagerPageFactory;
import org.nasdanika.taskmanager.ui.driver.pages.TaskManagerHomePage;
import org.openqa.selenium.WebDriver;
import org.nasdanika.webtest.WebTestUtil;
import org.osgi.service.component.ComponentContext;

public class TaskManagerPageFactoryImpl implements TaskManagerPageFactory {

	private String baseURL;
	
	public void activate(ComponentContext context) {
		baseURL = (String) context.getProperties().get("base-url");
		System.out.println("Page factory component activated with base URL '"+baseURL+"'");
	}
	
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
	
	@Override
	public TaskManagerHomePage createTaskManagerHomePage(WebDriver webDriver) {
		webDriver.get(getBaseURL()+"/index.html");
		TaskManagerHomePageImpl ret = WebTestUtil.initElements(webDriver, TaskManagerHomePageImpl.class);
		ret.setFactory(this);
		return ret;
	}
	
	public String getBaseURL() {
		return baseURL;
	}

}
