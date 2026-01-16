package PageClasses;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import BaseClasses.PageBaseClass;

public class BestBooksResultsPage extends PageBaseClass {

    WebDriver driver;

    public BestBooksResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* ================= RESULTS ================= */

    @FindBy(xpath = "//div[@id='listpromoproduct']//a[contains(@href,'/book/')]")
    private List<WebElement> bookLinks;

    public void openFirstNBooks(int count) {

        if (bookLinks.size() < count) {
            throw new RuntimeException("Not enough books found");
        }

        for (int i = 0; i < count; i++) {

            WebElement book = bookLinks.get(i);

            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", book);

            clickUsingJS(book);

            // After opening book → add to cart → go back
            ProductDetailsPage product =
                new ProductDetailsPage(driver);
            product.addBookToCart();

            driver.navigate().back();
            wait.until(ExpectedConditions.visibilityOf(bookLinks.get(0)));
        }
    }
}

