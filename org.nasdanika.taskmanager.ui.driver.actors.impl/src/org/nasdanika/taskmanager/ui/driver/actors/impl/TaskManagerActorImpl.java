package org.nasdanika.taskmanager.ui.driver.actors.impl;

import org.nasdanika.taskmanager.ui.driver.actors.TaskManagerActor;
import org.nasdanika.taskmanager.ui.driver.actors.TaskManagerActorFactory;
import org.nasdanika.taskmanager.ui.driver.pages.TaskEditDialog;
import org.nasdanika.taskmanager.ui.driver.pages.TaskManagerHomePage;
import org.nasdanika.taskmanager.ui.driver.pages.TaskStatus;
import org.nasdanika.taskmanager.ui.driver.pages.Theme;
import org.nasdanika.webtest.Page;
import org.openqa.selenium.WebDriver;

class TaskManagerActorImpl implements TaskManagerActor {

	private TaskManagerActorFactory factory;
	private Page<WebDriver> currentPage;
	private WebDriver webDriver;

	TaskManagerActorImpl(TaskManagerActorFactory factory, WebDriver webDriver) {
		this.factory = factory;
		this.webDriver = webDriver;
		currentPage = factory.getPageFactory().createTaskManagerHomePage(webDriver);		
	}

	@Override
	public Page<WebDriver> getCurrentPage() {
		return currentPage;
	}

	@Override
	public void setTheme(Theme theme) {
		currentPage = ((TaskManagerHomePage) currentPage).setTheme(theme);		
	}

	@Override
	public int createTask(String description, TaskStatus status) {
		TaskEditDialog dialog = ((TaskManagerHomePage) currentPage).newTask();
		dialog.setDescription(description);
		dialog.setStatus(status);
		currentPage = dialog.submit();
		// TODO - verification that the new task was created (number of rows got increased by 1) 
		// and that it has correct status and description. 
		return ((TaskManagerHomePage) currentPage).getTaskRows().size()-1;
	}

	@Override
	public void updateTask(int index, String description, TaskStatus status) {
		TaskEditDialog dialog = ((TaskManagerHomePage) currentPage).getTaskRows().get(index).edit();
		dialog.setDescription(description);
		dialog.setStatus(status);
		currentPage = dialog.submit();
		// TODO - verification that task row description and status got updated.
	}

	@Override
	public void deleteTask(int index) {
		currentPage = ((TaskManagerHomePage) currentPage).getTaskRows().get(index).delete(true);		
		// TODO - verification that the a task was deleted (number of rows got decreased by 1) 
	}

	@Override
	public void setTaskStatus(int index, TaskStatus status) {
		currentPage = ((TaskManagerHomePage) currentPage).getTaskRows().get(index).setStatus(status);
		// TODO - verification that the status got changed
	}

}
