package com.underarmour.pages;

import com.underarmour.commons.SortingTypeSearchPage;
import com.underarmour.components.HeaderMenu;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.ProductTile_product-item-link__4GZdP")
    private List<ExtendedWebElement> titlesOfProducts;

    @FindBy(css = "a.ProductTile_product-item-link__4GZdP")
    private List<ExtendedWebElement> linksOfProducts;

    @FindBy(xpath = "//span[@data-testid='price-display-list-price']")
    private List<ExtendedWebElement> pricesOfProducts;

    @FindBy(xpath = "//header")
    private HeaderMenu headerMenu;

    @FindBy(xpath = "//button[contains(@id, 'product-list-sorting')]")
    private ExtendedWebElement sortingProductButton;

    @FindBy(id = "%s")
    private ExtendedWebElement sortingOption;

    public List<ExtendedWebElement> getTitlesOfSearchingProducts(){
        return this.titlesOfProducts;
    }

    public List<ExtendedWebElement> getLinksOfSearchingProducts(){
        return this.linksOfProducts;
    }

    public ProductPage clickOnProductByNumber(Integer number){
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(getLinksOfSearchingProducts().get(number).getBy()))
                .click();
        return new ProductPage(driver);
    }

    public boolean isOpened(){
        return headerMenu.isUIObjectPresent() && sortingProductButton.isElementPresent();
    }

    public void clickOnSortButton(){
        sortingProductButton.click();
    }

    public void chooseSortOption(SortingTypeSearchPage sortingType){
        if (sortingOption.format(sortingType.getDOMId()).isVisible()){
            sortingOption.format(sortingType.getDOMId()).click();
        }else {
            Assert.fail(String.format("[SEARCH PAGE] soring option '%s' is not available (maybe sorting container is not opened, use method 'clickOnSortButton')", sortingOption.format(sortingType).getText()));
        }
    }
    public List<Double> getPricesForProducts(){
        return pricesOfProducts.stream().map(e -> {
            List<String> list = Arrays.asList(e.getText().replaceAll("[$]", "").split(" "));
            Collections.reverse(list);
            return list.get(0);
        }).map(Double::parseDouble).collect(Collectors.toList());
    }
}