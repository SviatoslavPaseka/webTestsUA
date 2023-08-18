package com.underarmour.pages;

import com.underarmour.components.HeaderMenu;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class HomePage extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @FindBy(xpath = "//header")
    private HeaderMenu headerMenu;

    @FindBy(css = "p.ElementTree_align--center__mROpQ")
    private ExtendedWebElement shopArrivalsText;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HeaderMenu getHeaderMenu() {
        return headerMenu;
    }

    public boolean isOpened(){
        LOGGER.info("HeaderMenu: " + headerMenu.isUIObjectPresent() + ", shopText: " + shopArrivalsText.isElementPresent());
        return headerMenu.isUIObjectPresent() && shopArrivalsText.isElementPresent();
    }
}
