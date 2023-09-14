package com.underarmour;

import com.ctc.wstx.shaded.msv_core.verifier.jarv.TheFactoryImpl;
import com.underarmour.commons.*;
import com.underarmour.components.HeaderMenu;
import com.underarmour.pages.*;
import com.zebrunner.agent.core.annotation.TestLabel;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

public class BaseFunctionalityTest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static String email;
    private static String pass;

//    @BeforeTest
//    public void getCrews(){
//        CryptoTool cryptoTool = CryptoToolBuilder.builder()
//                .chooseAlgorithm(Algorithm.find(Configuration.get(EncryptorConfiguration.Parameter.CRYPTO_ALGORITHM).orElse("Not found any crypto algorithm")))
//                .setKey(Configuration.get(EncryptorConfiguration.Parameter.CRYPTO_KEY_VALUE).orElse("Not found any crypto key"))
//                .build();
//        String CRYPTO_PATTERN = Configuration.get(EncryptorConfiguration.Parameter.CRYPTO_PATTERN).orElse("Not found any crypto pattern]");
//        email = cryptoTool.decrypt(R.TESTDATA.get("under_armour.web.email"), CRYPTO_PATTERN);
//        pass = cryptoTool.decrypt(R.TESTDATA.get("under_armour.web.password"), CRYPTO_PATTERN);
//        System.out.printf("\n=========================\nBEFORE TEST\nemail: %s, password: %s%n\n=========================\n", email, pass);
//    }
    @Test
    public void decryptTest(){
        System.out.printf("\n=========================\nTEST\nemail: %s, password: %s%n\n=========================\n", email, pass);
    }

    @Test
    public void checkingEnvInGithubActions(){
        System.out.println(System.getenv("EMAIL"));
        System.out.println(System.getenv("PASSWORD"));
    }
    @Test
    @MethodOwner(owner = "spaseka")
    @TestLabel(name = "feature", value = { "web", "acceptance" })
    public void searchTest() throws InterruptedException {
        getDriver().get("https://www.underarmour.com/en-us/");
        final String KEYWORD = "T-shirt";
        HomePage homePage = new HomePage(getDriver());
        CommonPage commonPage = new CommonPage(getDriver());
        Assert.assertTrue(homePage.isOpened(), "[HOME PAGE] is not opened");
        homePage.getHeaderMenu().inputKeywordForSearch(KEYWORD);
        homePage.getHeaderMenu().clickSearchButton();
        SearchPage searchPage = new SearchPage(getDriver());
        Assert.assertTrue(searchPage.isOpened(), "[SEARCH PAGE] is not opened");
        commonPage.closeAds();
        Thread.sleep(5000);
        searchPage.getTitlesOfSearchingProducts().forEach(e -> LOGGER.info(e.getText()));
        Assert.assertFalse(searchPage.getTitlesOfSearchingProducts().stream()
                .anyMatch(e -> e.getText().toLowerCase().contains(KEYWORD.toLowerCase())));
    }

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
        loginModalContainer
                .inputCredentials(R.TESTDATA.get("under_armour.web.email"), R.TESTDATA.get("under_armour.web.password"));
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
        HomePage homePage = commonPage.defaultLogin();
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

    @Test
    @MethodOwner(owner = "spaseka")
    @TestLabel(name = "feature", value = { "web", "acceptance" })
    public void sortTest() throws InterruptedException {
        final String keyword = "t-shirt";
        getDriver().get("https://www.underarmour.com/en-us/");
        CommonPage commonPage = new CommonPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
//        commonPage.acceptCookies();
        homePage.getHeaderMenu().inputKeywordForSearch(keyword);
        SearchPage searchPage = homePage.getHeaderMenu().clickSearchButton();
        Thread.sleep(5000);
        commonPage.closeAds();
        Thread.sleep(5000);
        searchPage.getPricesForProducts().forEach(e -> LOGGER.info("price: " + e));
        searchPage.clickOnSortButton();
        searchPage.chooseSortOption(SortingTypeSearchPage.PRICE_LOW_HIGH);
        searchPage.getPricesForProducts().forEach(e -> LOGGER.info("price: " + e));
    }

    @Test
    @MethodOwner(owner = "spaseka")
    @TestLabel(name = "feature", value = { "web", "acceptance" })
    public void checkoutTest(){
        final String keyword = "t-shirt";
        getDriver().get("https://www.underarmour.com/en-us/");
        CommonPage commonPage = new CommonPage(getDriver());
        HomePage homePage = commonPage.defaultLogin();
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

        ShippingStepPage shippingStepPage = cartPage.clickCheckoutButton();
        shippingStepPage.inputDataToAddressField(ShippingStepAddressFields.FIRST_NAME, "Sviat");
        shippingStepPage.inputDataToAddressField(ShippingStepAddressFields.LAST_NAME, "PASEKA");
        shippingStepPage.inputDataToAddressField(ShippingStepAddressFields.ADDRESS1, "Bykovska street");
        shippingStepPage.inputDataToAddressField(ShippingStepAddressFields.CITY, "Kiev");
        shippingStepPage.inputDataToAddressField(ShippingStepAddressFields.ZIP_CODE, "11111-1111");

        shippingStepPage.clickStateSelectingMenu();
        shippingStepPage.chooseStateByNumber(33);

        shippingStepPage.chooseShippingMethod(ShippingMethod.SHOP_RUNNER);

        VerifyingAddressWindow verifyingAddressWindow = shippingStepPage.clickContinueToPayment();
        PaymentStepPage paymentStepPage = verifyingAddressWindow.clickOkButton();

        paymentStepPage.clickRadioButton(PaymentRadioButton.CREDIT_CARD);
        paymentStepPage.inputValueIntoPaymentInputs(PaymentDataForCreditCard.CARD_NUMBER, "1111111111111111");
        paymentStepPage.inputValueIntoPaymentInputs(PaymentDataForCreditCard.EXPIRATION_DATE, "1205");
        paymentStepPage.inputValueIntoPaymentInputs(PaymentDataForCreditCard.SECURITY_CODE, "123");
        paymentStepPage.inputValueIntoPaymentInputs(PaymentDataForCreditCard.NAME_ON_CARD, "Sviat123");

        paymentStepPage.clickContinueButton();
    }

    @Test(invocationCount = 1)
    @MethodOwner(owner = "spaseka")
    @TestLabel(name = "feature", value = { "web", "acceptance" })
    public void checkoutWithSavedShippingDataTest() throws InterruptedException {
        final String keyword = "t-shirt";
        getDriver().get("https://www.underarmour.com/en-us/");
        CommonPage commonPage = new CommonPage(getDriver());
        HomePage homePage = commonPage.defaultLogin();
        commonPage.acceptCookies();
        HeaderMenu headerMenu = homePage.getHeaderMenu();
        headerMenu.clickCartButton().cleanCart();
        headerMenu.inputKeywordForSearch(keyword);
        SearchPage searchPage = headerMenu.clickSearchButton();
//        Thread.sleep(5000);
        ProductPage productPage = searchPage.clickOnProductByNumber(1);
        Assert.assertTrue(productPage.isOpened(), "[PRODUCT PAGE] is not opened");
        String productName = productPage.titleOfProductText();
        Double productPrice = productPage.getPriceOfProduct();
        LOGGER.info(String.format("PRODUCT NAME: %s, PRODUCT PRICE: %s", productName, productPrice.toString()));
        AddToCartConfirmationModalWindow confirmationModalWindow = productPage.clickAddToBagButton();
        Assert.assertTrue(confirmationModalWindow.isOpened(), "[ADD TO CART CONFIRMATION MODAL WINDOW] is not opened");
        CartPage cartPage = confirmationModalWindow.clickViewBagAndCheckoutButton();
        cartPage.clickCheckoutButton();
//        ShippingStepPage shippingStepPage = cartPage.clickCheckoutButton();
//        shippingStepPage.inputDataToAddressField(ShippingStepAddressFields.FIRST_NAME, "Sviat");
//        shippingStepPage.inputDataToAddressField(ShippingStepAddressFields.LAST_NAME, "PASEKA");
//        shippingStepPage.inputDataToAddressField(ShippingStepAddressFields.ADDRESS1, "Bykovska street");
//        shippingStepPage.inputDataToAddressField(ShippingStepAddressFields.CITY, "Kiev");
//        shippingStepPage.inputDataToAddressField(ShippingStepAddressFields.ZIP_CODE, "11111-1111");
//
//        shippingStepPage.clickStateSelectingMenu();
//        shippingStepPage.chooseStateByNumber(33);
//
//        shippingStepPage.chooseShippingMethod(ShippingMethod.SHOP_RUNNER);

//        VerifyingAddressWindow verifyingAddressWindow = shippingStepPage.clickContinueToPayment();
        PaymentStepPage paymentStepPage = new PaymentStepPage(getDriver());

        paymentStepPage.clickRadioButton(PaymentRadioButton.CREDIT_CARD);
        paymentStepPage.inputValueIntoPaymentInputs(PaymentDataForCreditCard.CARD_NUMBER, "1111111111111111");
        paymentStepPage.inputValueIntoPaymentInputs(PaymentDataForCreditCard.EXPIRATION_DATE, "1205");
        paymentStepPage.inputValueIntoPaymentInputs(PaymentDataForCreditCard.SECURITY_CODE, "123");
        paymentStepPage.inputValueIntoPaymentInputs(PaymentDataForCreditCard.NAME_ON_CARD, "Sviat123");

        paymentStepPage.clickContinueButton();
    }
}