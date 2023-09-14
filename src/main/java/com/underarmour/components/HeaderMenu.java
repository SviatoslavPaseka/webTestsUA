package com.underarmour.components;

import com.underarmour.commons.HeaderNavItemLinks;
import com.underarmour.commons.HeaderNavItems;
import com.underarmour.pages.CartPage;
import com.underarmour.pages.LoginModalContainer;
import com.underarmour.pages.SearchPage;
import com.zebrunner.carina.utils.factory.ICustomTypePageFactory;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderMenu extends AbstractUIObject implements ICustomTypePageFactory {

    @FindBy(xpath = "//button[contains(@aria-controls, 'login')]")
    private ExtendedWebElement loginButton;

    @FindBy(id = "search-input")
    private ExtendedWebElement searchInput;

    @FindBy(name = "search-button")
    private ExtendedWebElement searchButton;

    @FindBy(name = "clear-button")
    private ExtendedWebElement clearTextButton;

    @FindBy(id = "%s")
    private ExtendedWebElement navItemListByName;

    @FindBy(xpath = "//a[@data-testid='%s']")
    private ExtendedWebElement navLinksItem;

    @FindBy(xpath = "//button[contains(text(), 'My Account')]")
    private ExtendedWebElement myAccountButton;

    @FindBy(xpath = "//a[@href='/en-us/cart/']")
    private ExtendedWebElement cartButton;

    public HeaderMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void inputKeywordForSearch(String keyword){
        searchInput.type(keyword);
    }

    public void clearTextFromInputField(){
        if (clearTextButton.isElementPresent()){
            clearTextButton.click();
        }
    }

    public SearchPage clickSearchButton(){
        searchButton.click(20, ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.CustomLoader_container__giV5a")));
        return new SearchPage(driver);
    }

    public boolean checkIsNavItemVisible(HeaderNavItems headerNavItems){
        return navItemListByName.format(headerNavItems.getDOMId()).isVisible();
    }

    public void hoverNavItem(HeaderNavItemLinks links){
        navLinksItem.format(links.getDOMData_testid()).hover();
    }

    public LoginModalContainer clickLoginButton(){
        loginButton.click();
        return new LoginModalContainer(driver);
    }

    public boolean isMyAccountButtonPresent(){
        return myAccountButton.isElementPresent();
    }

    public CartPage clickCartButton(){
        cartButton.click();
        cartButton.click();
        return new CartPage(driver);
    }
}