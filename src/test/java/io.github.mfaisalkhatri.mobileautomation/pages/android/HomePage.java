package io.github.mfaisalkhatri.mobileautomation.pages.android;

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
            .findElement (MobileBy.id ("Text"));
    }

    public String getText () {
        return driverManager.getDriver ()
            .findElement (MobileBy.id ("Textbox"))
            .getText ();
    }

    public MobileElement notificationBtn() {
        return driverManager.getDriver ().findElement (MobileBy.id ("notification"));
    }

    public MobileElement notificationBar() {
        return driverManager.getDriver ().findElement (MobileBy.id ("action_bar"));
    }

    public MobileElement toastBtn() {
        return driverManager.getDriver ().findElement (MobileBy.id ("toast"));
    }

    public String toastMessage () {
        return wait.until (ExpectedConditions.presenceOfElementLocated (MobileBy.xpath ("//android.widget.Toast[1]"))).getText ();
    }

    public MobileElement geoLocationBtn() {
        return driverManager.getDriver ().findElement (MobileBy.id ("geoLocation"));
    }

    public MobileElement speedtTestBtn () {
        return driverManager.getDriver ().findElement (MobileBy.id ("speedTest"));
    }

    public MobileElement browserMenu () {
        return driverManager.getDriver ().findElement (MobileBy.AccessibilityId ("Browser"));
    }

}
