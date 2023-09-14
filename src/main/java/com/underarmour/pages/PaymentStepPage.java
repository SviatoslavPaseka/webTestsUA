package com.underarmour.pages;

import com.underarmour.commons.PaymentDataForCreditCard;
import com.underarmour.commons.PaymentRadioButton;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class PaymentStepPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public PaymentStepPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//label[@for = '%s']/span")
    private ExtendedWebElement paymentTypeRadioButton;

    @FindBy(xpath = "//input[@id = '%s']")
    private ExtendedWebElement paymentFormInput;

    @FindBy(css = "form > button.btn-primary")
    private ExtendedWebElement continueButton;

    @FindBy(xpath = "//iframe[@id='aurus-credit-card']")
    private ExtendedWebElement frameWithCreditCardData;

    public void clickContinueButton(){
        continueButton.click();
    }

    public void clickRadioButton(PaymentRadioButton paymentRadioButton){
        if (paymentTypeRadioButton.format(paymentRadioButton.getValueFor()).isElementPresent()){
        paymentTypeRadioButton.format(paymentRadioButton.getValueFor()).click(30, ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.CustomLoader_container__giV5a")));
        }
    }
    public void inputValueIntoPaymentInputs(PaymentDataForCreditCard dataForCreditCard, String data){
        driver.switchTo().frame(frameWithCreditCardData.getElement());
        LOGGER.info("Credit card dada: " + dataForCreditCard.getDomID() + " is present: " + paymentFormInput.format(dataForCreditCard.getDomID()).isElementPresent());
        paymentFormInput.format(dataForCreditCard.getDomID()).type(data);
        driver.switchTo().defaultContent();
    }
}