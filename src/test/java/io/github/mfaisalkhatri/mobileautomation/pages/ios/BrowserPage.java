package io.github.mfaisalkhatri.mobileautomation.pages.ios;

import static io.github.mfaisalkhatri.mobileautomation.utilities.Helper.clickOn;
import static io.github.mfaisalkhatri.mobileautomation.utilities.Helper.waitForSomeTime;

import io.appium.java_client.AppiumBy;
import io.github.mfaisalkhatri.mobileautomation.drivers.DriverManager;
import org.openqa.selenium.WebElement;

public class BrowserPage {

    private final DriverManager driverManager;

    public BrowserPage (final DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public WebElement findBtn () {
        return this.driverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("Find"));
    }

    public void navigateToHomePage () {
        this.driverManager.getDriver ()
            .navigate ()
            .back ();
    }

    public WebElement searchBox () {
        return this.driverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("url"));
    }

    public void searchFor (final String url) {
        searchBox ().sendKeys (url);
        clickOn (findBtn ());
        waitForSomeTime ();
    }

}
