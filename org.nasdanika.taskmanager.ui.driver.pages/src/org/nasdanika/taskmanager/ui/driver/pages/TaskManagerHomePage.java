package org.nasdanika.taskmanager.ui.driver.pages;

import java.util.List;

import org.nasdanika.webtest.Aliased;
import org.nasdanika.webtest.Description;
import org.nasdanika.webtest.Page;
import org.nasdanika.webtest.Title;
import org.openqa.selenium.WebDriver;

@Title("Task Manager Home Page")
@Description("Page class for testing Task Manager")
public interface TaskManagerHomePage extends Page<WebDriver>, Aliased {
	
	interface TaskRow extends Aliased {
		
		String getDescription();
		
		TaskEditDialog edit();
		
		TaskManagerHomePage delete(boolean confirm);
		
		TaskManagerHomePage setStatus(TaskStatus status);
		
	}
	
	TaskManagerHomePage setTheme(Theme theme);
	
	List<TaskRow> getTaskRows();
	
	TaskEditDialog newTask();		
	
}
