package TestClasses;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import BaseClasses.PageBaseClass;
import PageClasses.HomePage;
import PageClasses.SearchResultsPage;

public class SearchBookTest extends PageBaseClass {

    @Test
    public void verifyBookSearch() {

        invokeBrowser("chrome");

        HomePage home = openApplication();
        home.searchBook("Harry Potter");

        SearchResultsPage results =
                PageFactory.initElements(driver, SearchResultsPage.class);

        results.validateResultsDisplayed();
        results.printBookNamesAndPrices();

        takeScreenshot("BookSearchResult");

        closeBrowser();
    }
}