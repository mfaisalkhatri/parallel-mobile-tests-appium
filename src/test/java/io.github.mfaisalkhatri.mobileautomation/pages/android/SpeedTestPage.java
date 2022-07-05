package io.github.mfaisalkhatri.mobileautomation.pages.android;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.github.mfaisalkhatri.drivers.DriverManager;

public class SpeedTestPage {

    private DriverManager driverManager;

    public SpeedTestPage (final DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public MobileElement headerText () {
        return driverManager.getDriver ()
            .findElement (MobileBy.AccessibilityId ("Speedtest"));
    }

    public void navigateToHomePage () {
        driverManager.getDriver ()
            .navigate ()
            .back ();
    }

}
