package io.github.mfaisalkhatri.mobileautomation.pages.android;

import io.appium.java_client.AppiumBy;
import io.github.mfaisalkhatri.mobileautomation.drivers.DriverManager;
import org.openqa.selenium.WebElement;

public class SpeedTestPage {

    private final DriverManager driverManager;

    public SpeedTestPage (final DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public WebElement headerText () {
        return this.driverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("Speedtest"));
    }

    public void navigateToHomePage () {
        this.driverManager.getDriver ()
            .navigate ()
            .back ();
    }

}
