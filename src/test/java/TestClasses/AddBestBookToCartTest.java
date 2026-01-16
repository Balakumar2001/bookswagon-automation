package TestClasses;

import java.util.List;

import org.testng.annotations.Test;

import BaseClasses.PageBaseClass;
import PageClasses.HomePage;
import PageClasses.BestBooksResultsPage;
import PageClasses.CartPage;
import PageClasses.ProductDetailsPage;

public class AddBestBookToCartTest extends PageBaseClass {

	@Test
	public void addMultipleBooksAndVerifyCart() {

	    invokeBrowser("chrome");

	    HomePage home = openApplication();
	    home.openBestBooksOfTheYear();

	    BestBooksResultsPage results =
	        new BestBooksResultsPage(driver);

	    // Step 1: Add first 2 books
	    results.openFirstNBooks(2);

	    // Step 2: Open cart via header icon
	    home.openCart();

	    // Step 3: Update quantity
	    CartPage cart = new CartPage(driver);
	    

	    // Step 4: Retrieve cart contents
	    List<String> cartSummary = cart.getCartSummary();

	    System.out.println("===== CART SUMMARY =====");
	    for (String item : cartSummary) {
	        System.out.println(item);
	        //System.out.println("Cart items found: " + cart.getCartSummary().size());

	    }

	    closeBrowser();
	}

}
