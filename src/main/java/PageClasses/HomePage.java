package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;


import BaseClasses.PageBaseClass;

public class HomePage extends PageBaseClass {

    /*************** CONSTRUCTOR ****************/
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*************** LOCATORS ****************/

    // Search input box
    @FindBy(id = "inputbar")
    private WebElement searchBox;

    // Search button
    @FindBy(id = "btnTopSearch")
    private WebElement searchButton;

    @FindBy(id = "ctl00_imglogo")
    private WebElement homeLogo;

    
    @FindBy(xpath = "//a[contains(@href,'promo-best-seller/new-arrivals')]")
    WebElement newArrivalsLink;
    
 // Books menu (hover target)
    @FindBy(xpath = "//a[contains(@class,'dropdown-toggle') and contains(.,'Books')]")
    private WebElement booksMenu;

    // Best Books of the Year under Browse
    @FindBy(xpath = "//dd[@role='list']//a[normalize-space()='Best Books of the Year']")
    private WebElement bestBooksOfTheYear;
    
 // Cart icon in header
    @FindBy(xpath = "//a[contains(@href,'shoppingcart')]")
    private WebElement cartIcon;




    /*************** ACTION METHODS ****************/

    public void enterSearchText(String bookName) {
        waitUntilVisible(searchBox);
        searchBox.clear();
        searchBox.sendKeys(bookName);
    }

    public void clickSearchButton() {
        waitUntilClickable(searchButton);
        searchButton.click();
    }

    public void searchBook(String bookName) {
        enterSearchText(bookName);
        clickSearchButton();
    }
    
    public void clickNewArrivals() {
        waitUntilClickable(newArrivalsLink);
        newArrivalsLink.click();
    }
    
    public void openBestBooksOfTheYear() {

    	Actions actions = new Actions(driver);

        // Step 1: Hover on Books
        wait.until(ExpectedConditions.visibilityOf(booksMenu));
        actions.moveToElement(booksMenu).perform();

        // Step 2: Wait for Best Books of the Year to appear
        wait.until(ExpectedConditions.visibilityOf(bestBooksOfTheYear));

        // Step 3: Scroll and click
        scrollIntoView(bestBooksOfTheYear);
        clickUsingJS(bestBooksOfTheYear);

        
    }
    public void openCart() {

        wait.until(ExpectedConditions.visibilityOf(cartIcon));
        scrollIntoView(cartIcon);
        clickUsingJS(cartIcon);
    }
    public void clickHomeLogo() {

        // Wait until logo image is visible
        wait.until(ExpectedConditions.visibilityOf(homeLogo));

        // Scroll into view (safety)
        scrollIntoView(homeLogo);

        // Click using JS (avoid overlays)
        clickUsingJS(homeLogo);
    }





    /*************** VALIDATION METHODS ****************/

    public boolean isHomePageDisplayed() {
        return homeLogo.isDisplayed();
    }
}
