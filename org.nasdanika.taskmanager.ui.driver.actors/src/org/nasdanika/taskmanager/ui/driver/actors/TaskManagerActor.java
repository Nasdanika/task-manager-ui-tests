package org.nasdanika.taskmanager.ui.driver.actors;

import org.nasdanika.taskmanager.ui.driver.pages.TaskStatus;
import org.nasdanika.taskmanager.ui.driver.pages.Theme;
import org.nasdanika.webtest.Actor;
import org.nasdanika.webtest.Description;
import org.openqa.selenium.WebDriver;

@Description("Task Manager UI Tests Actor")
public interface TaskManagerActor extends Actor<WebDriver> {
	
//	@Description("Navigates to Task Manager Home Page")
//	TaskManagerHomePage navigateToTaskManagerHomePage();
	
	void setTheme(Theme theme);
	
	int createTask(String description, TaskStatus status);
	
	void updateTask(int index, String description, TaskStatus status);
		
	void deleteTask(int index);
	
	void setTaskStatus(int index, TaskStatus status);

}
