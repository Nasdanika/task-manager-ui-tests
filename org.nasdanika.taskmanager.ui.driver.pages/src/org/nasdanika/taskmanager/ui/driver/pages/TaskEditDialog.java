package org.nasdanika.taskmanager.ui.driver.pages;

import org.nasdanika.webtest.Aliased;
import org.nasdanika.webtest.Page;
import org.openqa.selenium.WebDriver;

public interface TaskEditDialog extends Page<WebDriver>, Aliased {
	
	String getDescription();
	
	TaskStatus getStatus();
	
	void setDescription(String description);
	
	void setStatus(TaskStatus status);
	
	TaskManagerHomePage submit();
	
	TaskManagerHomePage cancel();

}
