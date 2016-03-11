package org.nasdanika.taskmanager.ui.driver.pages.impl;

import org.nasdanika.taskmanager.ui.driver.pages.TaskEditDialog;
import org.nasdanika.taskmanager.ui.driver.pages.TaskManagerHomePage;
import org.nasdanika.taskmanager.ui.driver.pages.TaskStatus;
import org.nasdanika.webtest.ReflectivePageBase;
import org.nasdanika.webtest.Wait;
import org.nasdanika.webtest.WebTestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

@Wait(css=TaskEditDialogImpl.SUBMIT_BUTTON_CSS_SELECTOR)
public class TaskEditDialogImpl extends ReflectivePageBase<WebDriver> implements TaskEditDialog {
	
	static final String MODAL_DIV_CONTAINER_SELECTOR = "#task-modal > div > div";
	static final String SUBMIT_BUTTON_CSS_SELECTOR = MODAL_DIV_CONTAINER_SELECTOR + " > div.modal-footer > button:nth-child(1)";
	
	@FindBy(css=MODAL_DIV_CONTAINER_SELECTOR+" > div.modal-body > form > div:nth-child(1) > textarea")
	private WebElement description;

	@FindBy(css=MODAL_DIV_CONTAINER_SELECTOR+" > div.modal-body > form > div:nth-child(2) > select")
	private WebElement status;

	@FindBy(css=SUBMIT_BUTTON_CSS_SELECTOR)
	private WebElement submit;

	@FindBy(css=MODAL_DIV_CONTAINER_SELECTOR + " > div.modal-footer > button:nth-child(2)")
	private WebElement cancel;
	
	
	private WebDriver webDriver;
	private String alias;

	public TaskEditDialogImpl(WebDriver webDriver, String alias) {
		this.webDriver = webDriver;
		this.alias = alias;
	}

	@Override
	public WebDriver getWebDriver() {
		return webDriver;
	}

	@Override
	public String getDescription() {
		return description.getText();
	}

	@Override
	public TaskStatus getStatus() {
		// Probably buggy - shall look for label, no value.
		return TaskStatus.valueOf(status.getText());
	}

	@Override
	public void setDescription(String description) {
		this.description.clear();
		if (description!=null) {
			this.description.sendKeys(description);
		}
	}

	@Override
	public void setStatus(TaskStatus status) {
		new Select(this.status).selectByValue(status.label);
	}

	@Override
	public TaskManagerHomePage submit() {
		submit.click();
		return WebTestUtil.initElements(webDriver, TaskManagerHomePageImpl.class);
	}

	@Override
	public TaskManagerHomePage cancel() {
		cancel.click();
		return WebTestUtil.initElements(webDriver, TaskManagerHomePageImpl.class);
	}

	@Override
	public String getAlias() {
		return alias;
	}

}
