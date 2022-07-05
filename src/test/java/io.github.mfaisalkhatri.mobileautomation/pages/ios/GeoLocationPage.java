package io.github.mfaisalkhatri.mobileautomation.pages.ios;

import static io.github.mfaisalkhatri.utilities.Helper.clickOn;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.github.mfaisalkhatri.drivers.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GeoLocationPage {

    DriverManager driverManager;
    WebDriverWait wait;

    public GeoLocationPage (final DriverManager driverManager) {
        this.driverManager = driverManager;
        wait = new WebDriverWait (driverManager.getDriver (), 30);
    }

    public MobileElement banner () {
        return (MobileElement) wait.until (
            ExpectedConditions.presenceOfElementLocated (MobileBy.AccessibilityId ("banner")));
    }

    public MobileElement backBtn () {
        return driverManager.getDriver ()
            .findElement (MobileBy.AccessibilityId ("Back"));
    }

    public void navigateToHomePage () {
        clickOn (backBtn ());
    }

}
