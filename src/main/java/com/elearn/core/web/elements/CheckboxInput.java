package com.elearn.core.web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.elearn.core.web.WebComponent;

public class CheckboxInput extends WebComponent<CheckboxInput> {

	public CheckboxInput(WebDriver driver, By findByMethod) {
		super(driver, findByMethod);
	}
	
}
