package org.nasdanika.taskmanager.ui.driver.actors;

import org.nasdanika.taskmanager.ui.driver.pages.TaskManagerPageFactory;
import org.openqa.selenium.WebDriver;

public interface TaskManagerActorFactory {
	
	TaskManagerPageFactory getPageFactory();
	
	TaskManagerActor createTaskManagerActor(WebDriver webDriver);

}
