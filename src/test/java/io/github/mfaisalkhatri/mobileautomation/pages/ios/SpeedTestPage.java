package io.github.mfaisalkhatri.mobileautomation.pages.ios;

import io.appium.java_client.AppiumBy;
import io.github.mfaisalkhatri.mobileautomation.drivers.DriverManager;

public class SpeedTestPage {

    DriverManager driverManager;

    public SpeedTestPage (final DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public String headerText () {
        return this.driverManager.getDriver ()
            .findElement (AppiumBy.iOSClassChain ("**/XCUIElementTypeImage[`label == \"Speedtest\"`]"))
            .getText ();
    }

    public void navigateToHomePage () {
        this.driverManager.getDriver ()
            .navigate ()
            .back ();
    }
}
