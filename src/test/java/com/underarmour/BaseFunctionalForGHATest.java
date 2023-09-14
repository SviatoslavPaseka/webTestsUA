package com.underarmour;

import com.underarmour.components.HeaderMenu;
import com.underarmour.pages.*;
import com.zebrunner.agent.core.annotation.TestLabel;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

public class BaseFunctionalForGHATest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static String email;
    private static String pass;

    @Test
    @MethodOwner(owner = "spaseka")
    @TestLabel(name = "feature", value = { "web", "acceptance" })
    public void loginTest() {
        getDriver().get("https://www.underarmour.com/en-us/");
        HomePage homePage = new HomePage(getDriver());
        CommonPage commonPage = new CommonPage(getDriver());
//        commonPage.acceptCookies();
        Assert.assertTrue(homePage.isOpened(), "[HOME PAGE] is not opened");
        LoginModalContainer loginModalContainer = homePage.getHeaderMenu().clickLoginButton();
        Assert.assertTrue(loginModalContainer.isLoginContainerOpened(), "[LOGIN MODAL FORM] is no opened");
        System.out.println(System.getenv("EMAIL"));
        System.out.println(System.getenv("PASSWORD"));
        loginModalContainer
                .inputCredentials(System.getenv("EMAIL"), System.getenv("PASSWORD"));
        loginModalContainer.clickLoginButton();
        Assert.assertTrue(homePage.getHeaderMenu().isMyAccountButtonPresent(), "[HOME PAGE] user in not successfully logged in");
    }

    @Test
    @MethodOwner(owner = "spaseka")
    @TestLabel(name = "feature", value = { "web", "acceptance" })
    public void addToCartTest() throws InterruptedException {
        final String keyword = "t-shirt";
        getDriver().get("https://www.underarmour.com/en-us/");
        CommonPage commonPage = new CommonPage(getDriver());
        HomePage homePage = commonPage.defaultLoginForGHA();
        commonPage.acceptCookies();
        HeaderMenu headerMenu = homePage.getHeaderMenu();
        headerMenu.clickCartButton().cleanCart();
        headerMenu.inputKeywordForSearch(keyword);
        SearchPage searchPage = headerMenu.clickSearchButton();
        ProductPage productPage = searchPage.clickOnProductByNumber(1);
        Assert.assertTrue(productPage.isOpened(), "[PRODUCT PAGE] is not opened");
        String productName = productPage.titleOfProductText();
        Double productPrice = productPage.getPriceOfProduct();
        LOGGER.info(String.format("PRODUCT NAME: %s, PRODUCT PRICE: %s", productName, productPrice.toString()));
        AddToCartConfirmationModalWindow confirmationModalWindow = productPage.clickAddToBagButton();
        Assert.assertTrue(confirmationModalWindow.isOpened(), "[ADD TO CART CONFIRMATION MODAL WINDOW] is not opened");
        CartPage cartPage = confirmationModalWindow.clickViewBagAndCheckoutButton();
        Assert.assertEquals(cartPage.getNameProduct(0), productName, "[CART PAGE] product name is not the same that was added");
        Assert.assertEquals(cartPage.getPriceProduct(0), productPrice, "[CART PAGE] product price is not the same that was added");
        LOGGER.info("TOTAL CART PRICE: " + cartPage.getTotalPriceInCart());
        Thread.sleep(5000);
    }


}