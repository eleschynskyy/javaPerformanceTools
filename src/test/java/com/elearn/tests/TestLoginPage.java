package com.elearn.tests;

import org.testng.annotations.Test;

import com.elearn.core.CsvDataProvider;
import com.elearn.core.BaseTest;
import com.elearn.core.DriverMaster;
import com.elearn.core.web.pages.LoginPage;
import org.testng.Reporter;

//@Listeners({ com.eleks.wic.tests.ScreenShotOnFailure.class })
public class TestLoginPage extends BaseTest {

	@Test//(dataProvider = "CsvDataProviderAsObject", dataProviderClass = CsvDataProvider.class)
	public void userWithRightCredentialsCouldLoginIn() {
		LoginPage loginPage = new LoginPage(DriverMaster.getDriverInstance())
			.loadAndWaitUntilAvailable();
		Reporter.log("test finished");
	}

	/*@Test
	public void unsuccessfulLoginIncorrectPassword() {
		loginPage.load().loginToBravaisAs("admin", "incorrect password");
		assertTrue(loginPage.isAvailable());
		assertEquals(loginPage.alertText(),
				"The username or password is incorrect");
	}

	@Test
	public void unsuccessfulLoginIncorrectUsername() {
		loginPage.load().loginToBravaisAs("incorrect username", "tajemstvi");
		assertTrue(loginPage.isAvailable());
		assertEquals(loginPage.alertText(),
				"The username or password is incorrect");
	}

	@Test
	public void unsuccessfulLoginLeaveFieldsEmpty() {
		loginPage.load().loginToBravaisAs("", "");
		assertTrue(loginPage.isAvailable());
		assertEquals(loginPage.alertText(),
				"The username or password is incorrect");
	}

	@Test
	public void unsuccessfulLoginCheckCaseSensitivity() {
		loginPage.load().loginToBravaisAs("AdMin", "Tajemstvi");
		assertTrue(loginPage.isAvailable());
		assertEquals(loginPage.alertText(),
				"The username or password is incorrect");
	}

	@Test
	public void forgotPasswordPage() {
		loginPage.load().openForgotPasswordPage();
		assertEquals(forgotPasswordPage.formHeading(), "Forgot password?");
		assertEquals(
				forgotPasswordPage.descriptionText(),
				"Enter your username and we'll send a link to reset your password to the address we have for your account.");
	}

	@Test
	public void testForgotPasswordLeaveUsernameFieldEmpty() {
		loginPage.load().openForgotPasswordPage();
		forgotPasswordPage.fillForgotPasswordForm("");
		assertEquals(forgotPasswordPage.alertText(), "User is not registered");
	}

	@Test
	public void testForgotPasswordEnterInvalidUsername() {
		loginPage.load().openForgotPasswordPage();
		forgotPasswordPage.fillForgotPasswordForm("username");
		assertEquals(forgotPasswordPage.alertText(), "User is not registered");
	}

	@Test
	public void testForgotPasswordEnterValidData() {
		loginPage.load().openForgotPasswordPage();
		forgotPasswordPage.fillForgotPasswordForm("olha.tovstyak");
		assertEquals(forgotPasswordPage.descriptionText(), "Email was sent");
	}

	@Test
	public void openForgotPasswordFormAndPressCancel() {
		loginPage.load().openForgotPasswordPage();
		forgotPasswordPage.fillForgotPasswordForm("olha.tovstyak");

	}

	@Test
	public void resetPasswordLeavePasswordFieldsEmpty() {
		loginPage.load().openForgotPasswordPage();
		forgotPasswordPage.fillForgotPasswordForm("");

	}

	@Test
	public void accountDropDown() throws IOException {
		loginPage.load().loginToBravais();
		bravaisMainPage.openAccountDropDown();
		assertEquals(bravaisMainPage.profileLink(), "Profile");
		assertEquals(bravaisMainPage.preferencesLink(), "Preferences");
		assertEquals(bravaisMainPage.favoritesLink(), "Favorites");
		assertEquals(bravaisMainPage.logoutLink(), "Logout");
	}

	@Test
	public void logoutPage() throws IOException {
		loginPage.load().loginToBravais();
		bravaisMainPage.openAccountDropDown();
		bravaisMainPage.logoutFromBravais();
		assertEquals(loginPage.logoutLabel(), "You are logged out");
	}*/
}
