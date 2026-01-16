package TestClasses;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClasses.PageBaseClass;
import PageClasses.HomePage;
import PageClasses.BestBooksResultsPage;
import PageClasses.CartPage;

public class verifyCartIsEmptyInNewSession extends PageBaseClass {

    @Test
    public void verifyCartIsEmptyInNewSession() {

        // -------- SESSION 1 --------
        invokeBrowser("chrome");

        HomePage home = openApplication();
        home.openBestBooksOfTheYear();

        BestBooksResultsPage results =
                new BestBooksResultsPage(driver);
        results.openFirstNBooks(2);

        closeBrowser();

        // -------- SESSION 2 --------
        invokeBrowser("chrome");

        HomePage homeNewSession = openApplication();
        homeNewSession.openCart();

        CartPage cart = new CartPage(driver);
        List<String> cartSummary = cart.getCartSummary();

        System.out.println("Cart items in new session: " + cartSummary.size());

        Assert.assertEquals(cartSummary.size(), 0,
                "Cart is not empty in a new browser session");

        closeBrowser();
    }
}
