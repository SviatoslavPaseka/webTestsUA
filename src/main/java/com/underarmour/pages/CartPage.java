package com.underarmour.pages;

import com.underarmour.components.HeaderMenu;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class CartPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//header")
    private HeaderMenu headerMenu;

    @FindBy(xpath = "//a[@data-testid='checkout-order-checkout-button']")
    private ExtendedWebElement checkoutButton;

    @FindBy(xpath = "//a[@data-testid='cartitem-product-name-link']")
    private List<ExtendedWebElement> productNameLinks;

    @FindBy(xpath = "//div[@class='CartItem_price__lDsih']/div/span[@data-testid='price-display-list-price']")
    private List<ExtendedWebElement> productInCartPrices;

    @FindBy(xpath = "//div[@data-testid='order-totals-grand-total']")
    private ExtendedWebElement totalPriceByCart;

    @FindBy(xpath = "//span[contains(text(), 'Remove')][@aria-hidden]")
    private List<ExtendedWebElement> removeButtons;

    public boolean isOpened(){
        return headerMenu.isUIObjectPresent() &&
                checkoutButton.isElementPresent();
    }

    public HeaderMenu getHeaderMenu(){
        return this.headerMenu;
    }

    public ProductPage clickOnNameLinkProduct(Integer number){
        if (number > productNameLinks.size()) {
            Assert.fail("[CART PAGE] inputted number: " + number + " more than quantity or products in cart(" + productNameLinks.size() + ").");
        }
        productNameLinks.get(number).click();
        return new ProductPage(driver);
    }

    public String getNameProduct(Integer number){
        if (number > productNameLinks.size()) {
            Assert.fail("[CART PAGE] inputted number: " + number + " more than quantity or products in cart(" + productNameLinks.size() + ").");
        }
        return productNameLinks.get(number).getText();
    }

    public Double getPriceProduct(Integer number){
        if (number > productInCartPrices.size()) {
            Assert.fail("[CART PAGE] inputted number: " + number + " more than quantity or products in cart(" + productNameLinks.size() + ").");
        }
        return Double.parseDouble(productInCartPrices.get(number).getText().replaceAll("[$]", ""));
    }

    public Double getTotalPriceInCart(){
        return Double.parseDouble(totalPriceByCart.getText().replaceAll("[$]", ""));
    }

    public RemoveItemModalWindow clickRemoveButton(Integer number){
        if (removeButtons.size() < number){
            Assert.fail("[CART PAGE] there are only )" + removeButtons.size() + " remove buttons");
        }
        removeButtons.get(number).click();
        return new RemoveItemModalWindow(driver);
    }

    public CartPage cleanCart(){
        if (removeButtons.isEmpty()){
            LOGGER.info("[CART PAGE] cart is already clean");
        }else {
            for (int i = 0; i < removeButtons.size(); i++) {
                clickRemoveButton(i);
            }
        }
        return this;
    }
}