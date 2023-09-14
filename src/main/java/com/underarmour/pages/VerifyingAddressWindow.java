package com.underarmour.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class VerifyingAddressWindow extends AbstractPage {
    public VerifyingAddressWindow(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//button[contains(text(), 'OK')]")
    private ExtendedWebElement okButton;

    @FindBy(xpath = "//button[contains(text(), 'Cancel')]")
    private ExtendedWebElement cancelButton;

    public PaymentStepPage clickOkButton(){
        okButton.click();
        return new PaymentStepPage(driver);
    }

    public void clickCancelButton(){
        cancelButton.click();
    }
}