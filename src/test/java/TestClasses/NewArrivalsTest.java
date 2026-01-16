package TestClasses;

import java.util.List;

import org.testng.annotations.Test;

import BaseClasses.PageBaseClass;
import PageClasses.HomePage;
import PageClasses.NewArrivalsResultsPage;

public class NewArrivalsTest extends PageBaseClass {

    @Test
    public void verifyNewArrivalsFilterFlow() {

        invokeBrowser("chrome");

        // Step 1: Open home & click New Arrivals
        HomePage home = openApplication();
        home.clickNewArrivals();

        // Step 2: Apply filters
        NewArrivalsResultsPage results =
                new NewArrivalsResultsPage();

        results.sortByPriceLowToHigh();
        results.selectHardCoverBindingIfAvailable();
        results.selectEnglishLanguage();

        // Step 3: Retrieve results data
        int count = results.getResultsCount();
        List<String> titles = results.getAllBookTitles();
        List<Integer> prices = results.getAllDiscountedPrices();
        System.out.println("Total results found: " + count);
        System.out.println("===== NEW ARRIVALS RESULTS =====");

        for (int i = 0; i < titles.size(); i++) {
            System.out.println(titles.get(i) + " - ₹" + prices.get(i));
        }

        takeScreenshot("NewArrivals_Filtered_Result");

        closeBrowser();
    }
}
