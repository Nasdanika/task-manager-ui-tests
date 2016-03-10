package org.nasdanika.taskmanager.ui.driver.pages;

public enum TaskStatus {
	
	PENDING("Pending"),
	IN_PROGRESS("In progress"),
	COMPLETED("Completed");
	
	public final String label;

	private TaskStatus(String label) {
		this.label = label;
	}

}
