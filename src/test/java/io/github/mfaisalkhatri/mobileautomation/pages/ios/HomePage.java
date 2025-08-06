package io.github.mfaisalkhatri.mobileautomation.pages.ios;

import java.time.Duration;

import io.appium.java_client.AppiumBy;
import io.github.mfaisalkhatri.mobileautomation.drivers.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    DriverManager driverManager;
    WebDriverWait wait;

    public HomePage (final DriverManager driverManager) {
        this.driverManager = driverManager;
        this.wait = new WebDriverWait (driverManager.getDriver (), Duration.ofSeconds (20));
    }

    public WebElement browserMenu () {
        return this.driverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("Browser"));
    }

    public WebElement geoLocationBtn () {
        return this.driverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("geoLocation"));
    }

    public String getText () {
        return this.driverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("Textbox"))
            .getText ();
    }

    public WebElement notificationBar () {
        return this.wait.until (
            ExpectedConditions.presenceOfElementLocated (AppiumBy.accessibilityId ("NotificationShortLookView")));
    }

    public WebElement notificationBtn () {
        return this.driverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("notification"));
    }

    public WebElement speedtTestBtn () {
        return this.driverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("speedTest"));
    }

    public WebElement textBtn () {
        return this.driverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("Text"));
    }

    public WebElement toastBtn () {
        return this.driverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("toast"));
    }

    public String toastMessage () {
        return this.wait.until (ExpectedConditions.presenceOfElementLocated (
                AppiumBy.xpath ("//*[contains(@label, 'Toast should be visible')]")))
            .getText ();
    }

}
