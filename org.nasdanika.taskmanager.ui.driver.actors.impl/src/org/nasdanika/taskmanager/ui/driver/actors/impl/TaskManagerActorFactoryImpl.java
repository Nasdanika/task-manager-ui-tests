package org.nasdanika.taskmanager.ui.driver.actors.impl;

import org.nasdanika.taskmanager.ui.driver.pages.TaskManagerPageFactory;
import org.nasdanika.taskmanager.ui.driver.actors.TaskManagerActorFactory;
import org.nasdanika.taskmanager.ui.driver.actors.TaskManagerActor;
import org.nasdanika.webtest.WebTestUtil;
import org.openqa.selenium.WebDriver;
import org.osgi.service.component.ComponentContext;

public class TaskManagerActorFactoryImpl implements TaskManagerActorFactory {

	private TaskManagerPageFactory pageFactory;

	public void setPageFactory(TaskManagerPageFactory pageFactory) {
		this.pageFactory = WebTestUtil.proxyPageFactory(pageFactory);
	}

	@Override
	public TaskManagerActor createTaskManagerActor(WebDriver webDriver) {
		return new TaskManagerActorImpl(this, webDriver);
	}
	
	// For troubleshooting
	public void activate(ComponentContext context) {
		System.out.println("Task Manager UI Tests Actor Factory Component activated");
	}

	@Override
	public TaskManagerPageFactory getPageFactory() {
		return pageFactory;
	}

}
