package org.nasdanika.taskmanager.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.nasdanika.webtest.NasdanikaWebTestSuite;
import org.nasdanika.webtest.ResultsModel;
import org.nasdanika.webtest.Title;

@RunWith(NasdanikaWebTestSuite.class)
@Title("Task Manager UI Tests test report")
@SuiteClasses({TaskManagerTest.class})
@ResultsModel
public class TaskManagerTests {
	
}
