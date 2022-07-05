package io.github.mfaisalkhatri.mobileautomation.pages.android;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.github.mfaisalkhatri.drivers.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GeoLocationPage {

    private DriverManager driverManager;
    private WebDriverWait wait;

    public GeoLocationPage (final DriverManager driverManager) {
        this.driverManager = driverManager;
        wait = new WebDriverWait (driverManager.getDriver (), 30);
    }

    public MobileElement content () {
        return (MobileElement) wait.until (
            ExpectedConditions.presenceOfElementLocated (MobileBy.id ("android:id/content")));
    }

    public void navigateToHomePage () {
        driverManager.getDriver ()
            .navigate ()
            .back ();
    }

}
