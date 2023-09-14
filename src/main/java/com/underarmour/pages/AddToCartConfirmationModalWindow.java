package com.underarmour.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AddToCartConfirmationModalWindow extends AbstractPage {
    public AddToCartConfirmationModalWindow(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a.btn-primary")
    private ExtendedWebElement viewBagAndCheckoutButton;

    @FindBy(css = "div[class^='AddToCartConfirmationModal_cart-modal-footer__'] > button.btn-secondary")
    private ExtendedWebElement continueShoppingButton;

    @FindBy(xpath = "//button[@aria-label='Close Dialog'][@data-dismiss]")
    private ExtendedWebElement closeWindowButton;

    public boolean isOpened(){
        return continueShoppingButton.isElementPresent() &&
                closeWindowButton.isElementPresent();
    }

    public CartPage clickViewBagAndCheckoutButton(){
        viewBagAndCheckoutButton.click();
        return new CartPage(driver);
    }
}