package com.underarmour.pages;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginModalContainer extends AbstractPage {
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
        emailField.type(email);
    }

    public void inputPassword(String password){
        passwordField.type(password);
    }

    public void inputCredentials(String email, String password){
        inputEmail(email);
        inputPassword(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }
}