package org.nasdanika.taskmanager.ui.driver.pages.impl;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.taskmanager.ui.driver.pages.TaskEditDialog;
import org.nasdanika.taskmanager.ui.driver.pages.TaskManagerHomePage;
import org.nasdanika.taskmanager.ui.driver.pages.Theme;
import org.nasdanika.webtest.ReflectivePageBase;
import org.nasdanika.webtest.Wait;
import org.nasdanika.webtest.WebTestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@Wait(css=TaskManagerHomePageImpl.NEW_TASK_BUTTON_CSS_SELECTOR)
public class TaskManagerHomePageImpl extends ReflectivePageBase<WebDriver> implements TaskManagerHomePage {
	
	static final String NEW_TASK_BUTTON_CSS_SELECTOR = "#content-panel > button";
				
	private TaskManagerPageFactoryImpl factory;
	private WebDriver webDriver;
	
	@FindBy(css=NEW_TASK_BUTTON_CSS_SELECTOR)
	private WebElement newTaskButton;
	
	@FindBy(css="body > div > div.panel-heading > h3 > div > span > span")
	private WebElement themeToggle;
	
	@FindBy(css="body > div > div.panel-heading > h3 > div > span > ul")
	private WebElement themeDropDown;
	
	@FindBy(css="#content-panel > table > tbody")
	private WebElement taskTableBody;
	
	private WebDriverWait webDriverWait;

	public TaskManagerHomePageImpl(WebDriver webDriver) {
		this.webDriver = webDriver;
		this.webDriverWait = new WebDriverWait(webDriver, 10);
	}
	
	public void setFactory(TaskManagerPageFactoryImpl factory) {
		this.factory = factory;
	}
	
	@Override
	public WebDriver getWebDriver() {
		return webDriver;
	}

	@Override
	public TaskManagerHomePage setTheme(Theme theme) {
		themeToggle.click();
		// Selection by text doesn't work for some reason, using ordinal.
		String themeItemXPath = "li["+(theme.ordinal()+1)+"]/a";
		WebElement themeItem = webDriverWait.until(ExpectedConditions.visibilityOf(themeDropDown.findElement(By.xpath(themeItemXPath))));
		WebTestUtil.takeScreenshot("Theme drop-down");
		themeItem.click();
		WebTestUtil.takeScreenshot("Theme selected");
		return WebTestUtil.initElements(webDriver, TaskManagerHomePageImpl.class);
	}
	
	@Override
	public List<TaskRow> getTaskRows() {
		List<TaskRow> ret = new ArrayList<>();
		int idx = 0;
		for (WebElement row: taskTableBody.findElements(By.cssSelector("tr"))) {
			ret.add(new TaskRowImpl(webDriver, webDriverWait, ++idx, row));			
		}
		return ret;
	}

	@Override
	public TaskEditDialog newTask() {
		newTaskButton.click();
		return WebTestUtil.initElements(webDriver, TaskEditDialogImpl.class, "New Task Dialog");
	}

	@Override
	public String getAlias() {
		return "Home Page";
	}

}
