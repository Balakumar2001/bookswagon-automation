package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseClasses.PageBaseClass;

public class ProductDetailsPage extends PageBaseClass {

    WebDriver driver;

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* ================= ADD TO CART ================= */

    @FindBy(id = "btnAddtocart")
    private WebElement addToCartButton;

    public void addBookToCart() {

        scrollIntoView(addToCartButton);
        clickUsingJS(addToCartButton);
    }
   
}
	