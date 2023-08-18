package com.underarmour.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class RemoveItemModalWindow extends AbstractPage {
    public RemoveItemModalWindow(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.modal_modal-container__AWbQx > h1")
    private ExtendedWebElement title;

    @FindBy(css = "div.CartItem_modal-buttons__aqGY2 > button.btn-primary")
    private ExtendedWebElement removeItemButton;

    @FindBy(css = "div.CartItem_modal-buttons__aqGY2 > button.btn-secondary")
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