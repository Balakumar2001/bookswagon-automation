package PageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import BaseClasses.PageBaseClass;

public class SearchResultsPage extends PageBaseClass {

    // Root container
    private By resultsContainer = By.id("listSearchResult");

    // Each product card
    private By productCards =
            By.xpath("//div[@id='listSearchResult']/div");

    private By bookTitle =
            By.xpath(".//div[contains(@class,'title')]//a");

    private By bookPrice =
            By.xpath(".//div[contains(@class,'price-attrib')]//div[contains(@class,'price')]");

    public void validateResultsDisplayed() {

        // 1️⃣ Wait for container to be present
        wait.until(driver ->
                driver.findElement(resultsContainer).isDisplayed()
        );

        // 2️⃣ Wait for at least one product to load
        wait.until(driver ->
                driver.findElements(productCards).size() > 0
        );

        int count = driver.findElements(productCards).size();

        if (count == 0) {
            throw new RuntimeException("No search results found!");
        }

        System.out.println("Total products found: " + count);
    }

    public void printBookNamesAndPrices() {

        List<WebElement> products =
                driver.findElements(productCards);

        for (WebElement product : products) {

            String title =
                    product.findElement(bookTitle).getText();

            String price =
                    product.findElement(bookPrice).getText();

            System.out.println(title + " - " + price);
        }
    }
}
