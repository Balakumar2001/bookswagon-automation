package BaseClasses;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import PageClasses.HomePage;

public class PageBaseClass {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    // -------- EXTENT VARIABLES --------
    protected static ExtentReports extent;
    protected static ExtentTest test;

    /******************** BROWSER SETUP *************************/
    public void invokeBrowser(String browserName) {

        try {
            if (browserName.equalsIgnoreCase("chrome")) {

                System.setProperty("webdriver.chrome.driver",
                        System.getProperty("user.dir") + "/drivers/chromedriver.exe");

                driver = new ChromeDriver();
            }

            else if (browserName.equalsIgnoreCase("edge")) {

                System.setProperty("webdriver.edge.driver",
                        System.getProperty("user.dir") + "/drivers/msedgedriver.exe");

                driver = new EdgeDriver();
            }

            else {
                throw new RuntimeException("Unsupported browser: " + browserName);
            }

        } catch (Exception e) {
            throw new RuntimeException("Browser launch failed", e);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /******************** OPEN APPLICATION *************************/
    public HomePage openApplication() {
        driver.get("https://www.bookswagon.com");
        return PageFactory.initElements(driver, HomePage.class);
    }

    /******************** WAIT METHODS *************************/
    public void waitUntilVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /******************** JS UTILITIES *************************/
    public void clickUsingJS(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    public void sendKeysUsingJS(WebElement element, String value) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].value=arguments[1];", element, value);
    }

    /******************** PAGE SCROLL *************************/
    public void scrollDownBy(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, arguments[0]);", pixels);
    }

    /******************** SCROLL UTILITIES *************************/
    public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            element
        );
    }

    /******************** SCREENSHOT *************************/
    public void takeScreenshot(String fileName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(src, new File(
                    System.getProperty("user.dir") + "/Screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }

    /******************** EXTENT SCREENSHOT *************************/
    public String captureScreenshot(String testName) {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path =
            System.getProperty("user.dir") +
            "/Screenshots/" + testName + ".png";

        try {
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    /******************** CLOSE BROWSER *************************/
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
