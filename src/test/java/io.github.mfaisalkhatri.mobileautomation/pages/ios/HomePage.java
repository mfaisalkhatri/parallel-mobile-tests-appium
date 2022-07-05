package io.github.mfaisalkhatri.mobileautomation.pages.ios;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.github.mfaisalkhatri.drivers.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    DriverManager driverManager;
    WebDriverWait wait;

    public HomePage (final DriverManager driverManager) {
        this.driverManager = driverManager;
        wait = new WebDriverWait (driverManager.getDriver (), 20);
    }

    public MobileElement textBtn () {
        return driverManager.getDriver ()
            .findElement (MobileBy.AccessibilityId ("Text"));
    }

    public String getText () {
        return driverManager.getDriver ()
            .findElement (MobileBy.AccessibilityId ("Textbox"))
            .getText ();
    }

    public MobileElement notificationBtn() {
        return driverManager.getDriver ().findElement (MobileBy.AccessibilityId ("notification"));
    }

    public MobileElement notificationBar() {
           return (MobileElement)wait.until (ExpectedConditions.presenceOfElementLocated (MobileBy.AccessibilityId ("NotificationShortLookView")));
    }

    public MobileElement toastBtn() {
        return driverManager.getDriver ().findElement (MobileBy.AccessibilityId ("toast"));
    }

    public String toastMessage () {
        return wait.until (ExpectedConditions.presenceOfElementLocated (MobileBy.xpath ("//*[contains(@label, 'Toast should be visible')]"))).getText ();
    }

    public MobileElement geoLocationBtn() {
        return driverManager.getDriver ().findElement (MobileBy.AccessibilityId ("geoLocation"));
    }

    public MobileElement speedtTestBtn () {
        return driverManager.getDriver ().findElement (MobileBy.AccessibilityId ("speedTest"));
    }

    public MobileElement browserMenu () {
        return driverManager.getDriver ().findElement (MobileBy.AccessibilityId ("Browser"));
    }

}
