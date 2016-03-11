package org.nasdanika.taskmanager.ui.driver.pages.impl;

import org.nasdanika.taskmanager.ui.driver.pages.TaskEditDialog;
import org.nasdanika.taskmanager.ui.driver.pages.TaskManagerHomePage;
import org.nasdanika.taskmanager.ui.driver.pages.TaskManagerHomePage.TaskRow;
import org.nasdanika.taskmanager.ui.driver.pages.TaskStatus;
import org.nasdanika.webtest.WebTestUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class TaskRowImpl implements TaskRow {
	
	private int idx;
	private WebElement rowElement;
	private WebDriver webDriver;
	private WebDriverWait webDriverWait;

	TaskRowImpl(WebDriver webDriver, WebDriverWait webDriverWait, int idx, WebElement rowElement) {
		this.webDriver = webDriver;
		this.webDriverWait = webDriverWait;
		this.idx = idx;
		this.rowElement = rowElement;
	}

	@Override
	public String getAlias() {
		return "Task Row "+idx;
	}

	@Override
	public String getDescription() {
		return rowElement.findElement(By.cssSelector("td:nth-child(1) > span")).getText();
	}

	@Override
	public TaskEditDialog edit() {
		rowElement.findElement(By.cssSelector("td:nth-child(1) > button:nth-child(2)")).click();
		return WebTestUtil.initElements(webDriver, TaskEditDialogImpl.class, "Edit Task #"+idx+" Dialog");
	}

	@Override
	public TaskManagerHomePage delete(boolean confirm) {
		rowElement.findElement(By.cssSelector("td:nth-child(1) > button:nth-child(1)")).click();
		webDriverWait.until(ExpectedConditions.alertIsPresent());
		Alert alert = webDriver.switchTo().alert();
		if (confirm) {
			alert.accept();
		} else {
			alert.dismiss();
		}		
		webDriverWait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
		return WebTestUtil.initElements(webDriver, TaskManagerHomePageImpl.class);
	}

	@Override
	public TaskManagerHomePage setStatus(TaskStatus status) {
		rowElement.findElement(By.cssSelector("td:nth-child(2) > span.dropdown > a")).click();
		WebElement statusDropDown = rowElement.findElement(By.cssSelector("td:nth-child(2) > span > ul"));
		String statusItemXPath = "li["+(status.ordinal()+1)+"]/a";
		WebElement statusItem = webDriverWait.until(ExpectedConditions.visibilityOf(statusDropDown.findElement(By.xpath(statusItemXPath))));
		WebTestUtil.takeScreenshot("Status drop-down");
		statusItem.click();
		return WebTestUtil.initElements(webDriver, TaskManagerHomePageImpl.class);
	}
	
}

