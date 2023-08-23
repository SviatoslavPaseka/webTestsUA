package com.underarmour.pages;

import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class CommonPage extends AbstractPage {
    public CommonPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//button[@data-testid='close-button']")
    private ExtendedWebElement closeAdsButton;

    @FindBy(xpath = "//button[@id='truste-consent-button']")
    private ExtendedWebElement acceptCookiesButton;

    public void closeAds(){
        ExtendedWebElement button = (ExtendedWebElement) new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(closeAdsButton.getBy()));
        button.click();
    }

    public HomePage loginMethod(String email, String password){
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isOpened(), "[HOME PAGE] is not opened");
        LoginModalContainer loginModalContainer = homePage.getHeaderMenu().clickLoginButton();
        Assert.assertTrue(loginModalContainer.isLoginContainerOpened(), "[LOGIN MODAL FORM] is no opened");
        loginModalContainer
                .inputCredentials(email, password);
        loginModalContainer.clickLoginButton();
        Assert.assertTrue(homePage.getHeaderMenu().isMyAccountButtonPresent(), "[HOME PAGE] user in not successfully logged in");
        return homePage;
    }

    public HomePage defaultLogin(){
        return loginMethod(R.TESTDATA.get("under_armour.web.email"), R.TESTDATA.get("under_armour.web.password"));
    }

    public void acceptCookies(){
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(acceptCookiesButton.getBy()))
                .click();
    }
}