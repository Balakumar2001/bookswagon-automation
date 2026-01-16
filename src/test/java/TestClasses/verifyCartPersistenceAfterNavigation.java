package TestClasses;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClasses.PageBaseClass;
import PageClasses.HomePage;
import PageClasses.BestBooksResultsPage;
import PageClasses.CartPage;

public class verifyCartPersistenceAfterNavigation extends PageBaseClass {

    @Test
    public void verifyCartPersistenceAfterNavigation() {

        invokeBrowser("chrome");

        // Step 1: Open Home Page
        HomePage home = openApplication();

        // Step 2: Navigate to Best Books of the Year
        home.openBestBooksOfTheYear();

        // Step 3: Add first 2 books to cart
        BestBooksResultsPage results =
                new BestBooksResultsPage(driver);
        results.openFirstNBooks(2);

        // Step 4: Navigate back to Home using logo
        home.clickHomeLogo();

        // Step 5: Open Cart from header icon
        home.openCart();

        // Step 6: Retrieve cart details
        CartPage cart = new CartPage(driver);
        List<String> cartSummary = cart.getCartSummary();

        System.out.println("===== CART PERSISTENCE SUMMARY =====");
        for (String item : cartSummary) {
            System.out.println(item);
        }

        // Step 7: Validation
        Assert.assertTrue(cartSummary.size() > 0,
                "Cart is empty after navigating back to Home");

        closeBrowser();
    }
}
