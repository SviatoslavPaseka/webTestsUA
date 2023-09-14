package com.underarmour.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class RemoveItemModalWindow extends AbstractPage {
    public RemoveItemModalWindow(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div[class^='modal_modal-container__'] > h1")
    private ExtendedWebElement title;

    @FindBy(css = "div[class^='CartItem_modal-buttons__'] > button.btn-primary")
    private ExtendedWebElement removeItemButton;

    @FindBy(css = "div[class^='CartItem_modal-buttons__'] > button.btn-secondary")
    private ExtendedWebElement cancelRemovingButton;

    public boolean isOpened(){
        return title.isElementPresent() &&
                removeItemButton.isElementPresent();
    }

    public CartPage clickOnRemoveButton(){
        removeItemButton.click();
        return new CartPage(driver);
    }
}