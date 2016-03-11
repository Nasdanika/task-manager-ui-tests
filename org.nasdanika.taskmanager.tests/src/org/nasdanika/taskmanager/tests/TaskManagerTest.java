package org.nasdanika.taskmanager.tests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.nasdanika.taskmanager.ui.driver.actors.TaskManagerActor;
import org.nasdanika.taskmanager.ui.driver.actors.TaskManagerActorFactory;
import org.nasdanika.taskmanager.ui.driver.pages.TaskStatus;
import org.nasdanika.taskmanager.ui.driver.pages.Theme;
import org.nasdanika.webtest.ActorFactory;
import org.nasdanika.webtest.Description;
import org.nasdanika.webtest.NasdanikaWebTestRunner;
import org.nasdanika.webtest.Pending;
import org.nasdanika.webtest.Screenshot;
import org.nasdanika.webtest.WebTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(NasdanikaWebTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Description("Tests of Task Manager UI Tests")
public class TaskManagerTest implements WebTest<WebDriver> {
	private WebDriver driver;
	
	@Override
	public WebDriver getWebDriver() {
		return driver;
	}
		
	@ActorFactory
	public TaskManagerActorFactory actorFactory;

	@Before
	public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS); //.implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	@Description("Sets task manager theme")
	@Screenshot({Screenshot.When.AFTER, Screenshot.When.EXCEPTION})
	public void testSetTheme() throws Exception {
		TaskManagerActor actor = actorFactory.createTaskManagerActor(getWebDriver());
		actor.setTheme(Theme.Sandstone);
		actor.setTheme(Theme.Bootstrap);
	}
	
	@Test
	@Screenshot({Screenshot.When.AFTER, Screenshot.When.EXCEPTION})
	public void testCreateTask() throws Exception {
		TaskManagerActor actor = actorFactory.createTaskManagerActor(getWebDriver());
		actor.createTask("Some rather important task!", TaskStatus.IN_PROGRESS);
	}
	
	@Test
	@Screenshot({Screenshot.When.AFTER, Screenshot.When.EXCEPTION})
	public void testDeleteTask() throws Exception {
		TaskManagerActor actor = actorFactory.createTaskManagerActor(getWebDriver());
		actor.deleteTask(3);
	}
	
	@Test
	@Screenshot({Screenshot.When.AFTER, Screenshot.When.EXCEPTION})
	public void testEditTask() throws Exception {
		TaskManagerActor actor = actorFactory.createTaskManagerActor(getWebDriver());
		actor.updateTask(4, "New description", TaskStatus.IN_PROGRESS);
	}
		
	@Test
	@Screenshot({Screenshot.When.AFTER, Screenshot.When.EXCEPTION})
	public void testSetTaskStatus() throws Exception {
		TaskManagerActor actor = actorFactory.createTaskManagerActor(getWebDriver());
		actor.setTaskStatus(2, TaskStatus.COMPLETED);
	}
	
	@After
	public void quitDriver() throws Exception {
		if (driver!=null) {
	        driver.quit();
	        driver = null;
		}
	}

	@Override
	public long getScreenshotDelay() {
		return 250;
	}
	
}
