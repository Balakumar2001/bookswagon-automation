package PageClasses;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseClasses.PageBaseClass;

public class CartPage extends PageBaseClass {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* ================= PRODUCT TITLES ================= */

    @FindBy(xpath = "//label[contains(@id,'lblProductTitle')]")
    private List<WebElement> productTitles;

    /* ================= DATA RETRIEVAL ================= */

    public List<String> getCartSummary() {

        List<String> summary = new ArrayList<>();

        // Total quantity hidden input
        WebElement totalQtyHidden =
            driver.findElement(
                By.xpath("//input[contains(@name,'hdnTotalQty')]"));

        String totalQty = totalQtyHidden.getAttribute("value");

        for (WebElement title : productTitles) {

            String bookName = title.getText().trim();

            summary.add(bookName + " | Qty: " + totalQty);
        }

        return summary;
    }
}
