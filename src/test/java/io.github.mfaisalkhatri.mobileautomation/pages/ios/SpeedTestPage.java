package io.github.mfaisalkhatri.mobileautomation.pages.ios;

import io.appium.java_client.MobileBy;
import io.github.mfaisalkhatri.drivers.DriverManager;

public class SpeedTestPage {

    DriverManager driverManager;

    public SpeedTestPage (final DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public String headerText () {
        return driverManager.getDriver ()
            .findElement (MobileBy.iOSClassChain ("**/XCUIElementTypeImage[`label == \"Speedtest\"`]"))
            .getText ();
    }

    public void navigateToHomePage () {
        driverManager.getDriver ()
            .navigate ()
            .back ();
    }
}
