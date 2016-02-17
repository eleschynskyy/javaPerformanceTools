package com.elearn.core.web;

import static com.elearn.core.Configuration.getConfig;

import org.openqa.selenium.WebDriver;

import com.elearn.core.Configuration;
import com.elearn.core.Environment;

public abstract class WebPage<T extends WebPage<T>> extends Component<T> {

	private static final Configuration CONFIG = getConfig();
	private static final Environment ENVIRONMENT = CONFIG
			.getEnvironmentSettings();
	protected static final String BASE_URL = ENVIRONMENT.scheme + "://"
			+ ENVIRONMENT.host;

	public WebPage(WebDriver driver) {
		super(driver);
	}

	public abstract T load();

	public T loadAndWaitUntilAvailable() {
		load();
		return waitUntilAvailable();
	}

}
