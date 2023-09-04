package com.underarmour.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class LoginModalContainer extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public LoginModalContainer(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[contains(@id, 'email-required')]")
    private ExtendedWebElement emailField;

    @FindBy(xpath = "//input[contains(@id, 'password-required')]")
    private ExtendedWebElement passwordField;

    @FindBy(css = "button.btn-primary")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "//p[@data-testid='invalid-creds']")
    private ExtendedWebElement invalidCredErrorMsg;

    public boolean isLoginContainerOpened(){
        return emailField.isElementPresent() && passwordField.isElementPresent();
    }

    public void inputEmail(String email){
        LOGGER.info("inputting email");
        emailField.type(email);
    }

    public void inputPassword(String password){
        LOGGER.info("inputting password");
        passwordField.type(password);
    }

    public void inputCredentials(String email, String password){
        LOGGER.info("inputting creds");
        inputEmail(email);
        inputPassword(password);
    }

    public void clickLoginButton(){
        LOGGER.info("click login button");
        loginButton.click();
    }
}