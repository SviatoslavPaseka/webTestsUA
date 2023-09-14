package com.underarmour.pages;

import com.underarmour.components.HeaderMenu;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends AbstractPage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div[class^='ProductDetailContent_image-container__'] > div + div > button > img")
    private ExtendedWebElement firstImageOnPage;

    @FindBy(css = "div[class^='ProductDetailContent_productName__'] > h1")
    private ExtendedWebElement titleOfProduct;

    @FindBy(xpath = "//div[@id='product-price']/div/span")
    private ExtendedWebElement priceOfCurrentProduct;

    @FindBy(id = "native-submit")
    private ExtendedWebElement addToBagButton;

    @FindBy(xpath = "//header")
    private HeaderMenu headerMenu;

    public boolean isOpened(){
        return firstImageOnPage.isElementPresent() &&
                titleOfProduct.isElementPresent();
    }

    public AddToCartConfirmationModalWindow clickAddToBagButton(){
        addToBagButton.click();
        return new AddToCartConfirmationModalWindow(driver);
    }

    public String titleOfProductText(){
        return titleOfProduct.getText();
    }
    public Double getPriceOfProduct(){
        return Double.parseDouble(priceOfCurrentProduct.getText().replaceAll("[$]", "").substring(0, 5));
    }
}