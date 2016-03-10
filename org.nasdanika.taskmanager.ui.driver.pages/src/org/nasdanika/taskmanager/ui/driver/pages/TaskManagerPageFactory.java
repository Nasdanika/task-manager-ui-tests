package org.nasdanika.taskmanager.ui.driver.pages;

import org.openqa.selenium.WebDriver;

public interface TaskManagerPageFactory {
	
	TaskManagerHomePage createTaskManagerHomePage(WebDriver webDriver);
	
}
