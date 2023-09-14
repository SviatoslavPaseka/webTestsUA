package com.underarmour.pages;

import com.underarmour.commons.ShippingMethod;
import com.underarmour.commons.ShippingStepAddressFields;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.lang.invoke.MethodHandles;

public class ShippingStepPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public ShippingStepPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='%s']")
    private ExtendedWebElement shippingAddressFields;

    @FindBy(xpath = "//label[@for='%s']")
    private ExtendedWebElement shippingMethodButton;

    @FindBy(xpath = "//button[@id='btn-shipping-submit']")
    private ExtendedWebElement continueToPayment;

    @FindBy(xpath = "(//ul[@id='stateCode-listbox']/li)[%s]")
    private ExtendedWebElement stateChoosing;

    @FindBy(xpath = "//ul[@id='stateCode-listbox']")
    private ExtendedWebElement stateChoosingContainer;

    @FindBy(xpath = "//h2[@id='shipping-header']/following-sibling::button")
    private ExtendedWebElement editingAddressButton;

    public VerifyingAddressWindow clickContinueToPayment(){
        continueToPayment.click();
        return new VerifyingAddressWindow(driver);
    }

    public void inputDataToAddressField(ShippingStepAddressFields addressFields, String data) {
        if (!editingAddressButton.isElementPresent()) {
            if (addressFields != ShippingStepAddressFields.STATE) {
                shippingAddressFields.format(addressFields.getId()).type(data);
            } else {
                LOGGER.warn("[SHIPPING STEP PAGE] impossible to type text into drop-down menu");
            }
        }else {
            LOGGER.info("[SHIPPING STEP PAGE] data is already inputted (account has saved shipping data)");
        }
    }

    public void clickStateSelectingMenu(){
        if (!editingAddressButton.isElementPresent()) {
            shippingAddressFields.format(ShippingStepAddressFields.STATE.getId()).click();
        }else {
            LOGGER.info("[SHIPPING STEP PAGE] data is already inputted (account has saved shipping data)");
        }
    }

    public void chooseShippingMethod(ShippingMethod shippingMethod){
        if (!editingAddressButton.isElementPresent()) {
            shippingMethodButton.format(shippingMethod.getForValue()).click();
        }else {
            LOGGER.info("[SHIPPING STEP PAGE] data is already inputted (account has saved shipping data)");
        }
    }

    public void chooseStateByNumber(Integer number){
        if (!editingAddressButton.isElementPresent()) {
            if (stateChoosingContainer.isElementPresent()){
                Assert.fail("[SHIPPING STEP PAGE] impossible to click on state. Maybe state choosing is not opened");
            }
            stateChoosing.format(number).click();
        }else {
            LOGGER.info("[SHIPPING STEP PAGE] data is already inputted (account has saved shipping data)");
        }

    }
}