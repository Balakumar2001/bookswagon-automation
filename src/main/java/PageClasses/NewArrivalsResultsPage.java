package PageClasses;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import BaseClasses.PageBaseClass;

public class NewArrivalsResultsPage extends PageBaseClass {

    /* ================= SORT ================= */

    private By sortDropdown = By.id("ddlSort");

    public void sortByPriceLowToHigh() {
        wait.until(driver -> driver.findElement(sortDropdown).isDisplayed());
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByValue("Product_ActualPrice asc");
    }

    /* ================= BINDING ================= */

    private By hardCoverLabel =
        By.xpath("//ul[contains(@class,'list-unstyled')]//a[normalize-space()='Hard Cover']");

    public void selectHardCoverBindingIfAvailable() {

        scrollDownBy(400);

        if (driver.findElements(hardCoverLabel).isEmpty()) {
            System.out.println("Binding filter not available for this category");
            return;
        }

        WebElement hardCover =
            wait.until(ExpectedConditions.presenceOfElementLocated(hardCoverLabel));

        scrollIntoView(hardCover);
        clickUsingJS(hardCover);
    }

    /* ================= LANGUAGE ================= */

    private By englishLink =
        By.xpath("//ul[contains(@class,'list-unstyled')]//a[normalize-space()='English']");

    public void selectEnglishLanguage() {

        scrollDownBy(600);

        WebElement english =
            wait.until(ExpectedConditions.presenceOfElementLocated(englishLink));

        scrollIntoView(english);
        clickUsingJS(english);
    }

    /* ================= RESULTS DATA ================= */

    private By productCards =
        By.xpath("//div[@id='listSearchResult']/div");

    private By bookTitle =
        By.xpath(".//div[contains(@class,'title')]//a");

    private By bookPrice =
        By.xpath(".//div[contains(@class,'price-attrib')]//div[contains(@class,'price')]");

    public List<String> getAllBookTitles() {

        List<String> titles = new ArrayList<>();

        List<WebElement> products =
            wait.until(driver -> driver.findElements(productCards));

        for (WebElement product : products) {
            titles.add(product.findElement(bookTitle).getText().trim());
        }

        return titles;
    }

    public List<Integer> getAllDiscountedPrices() {

        List<Integer> prices = new ArrayList<>();

        List<WebElement> products =
            wait.until(driver -> driver.findElements(productCards));

        for (WebElement product : products) {

            String priceText =
                product.findElement(bookPrice).getText().trim();

            // Take last line (discounted price)
            String[] split = priceText.split("\n");
            String finalPrice = split[split.length - 1];

            prices.add(
                Integer.parseInt(finalPrice.replaceAll("[^0-9]", ""))
            );
        }

        return prices;
    }
    public int getResultsCount() {

        List<WebElement> products =
            wait.until(driver -> driver.findElements(productCards));

        return products.size();
    }

}
