package org.nasdanika.taskmanager.ui.driver.pages;

import org.nasdanika.webtest.Page;
import org.openqa.selenium.WebDriver;

public interface TaskEditDialog extends Page<WebDriver> {
	
	String getDescription();
	
	TaskStatus getStatus();
	
	void setDescription(String description);
	
	void setStatus(TaskStatus status);
	
	TaskManagerHomePage submit();
	
	TaskManagerHomePage cancel();

}
