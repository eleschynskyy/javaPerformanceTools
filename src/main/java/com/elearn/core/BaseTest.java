package com.elearn.core;

import static com.elearn.core.Configuration.setGlobalEnvironment;
import static com.elearn.core.DriverMaster.startDriverInstance;
import static com.elearn.core.DriverMaster.stopDriverInstance;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "browser", "environment" })
	public void setUp(@Optional("chrome") String browser,
			@Optional("qa") String environment) {
		startDriverInstance(browser);
		setGlobalEnvironment(environment);
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		stopDriverInstance();
	}

}
