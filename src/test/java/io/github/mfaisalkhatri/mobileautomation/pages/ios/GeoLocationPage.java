package io.github.mfaisalkhatri.mobileautomation.pages.ios;

import static io.github.mfaisalkhatri.mobileautomation.utilities.Helper.clickOn;

import io.appium.java_client.AppiumBy;
import io.github.mfaisalkhatri.mobileautomation.drivers.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GeoLocationPage {

    DriverManager driverManager;
    WebDriverWait wait;

    public GeoLocationPage (final DriverManager driverManager) {
        this.driverManager = driverManager;
        this.wait = new WebDriverWait (driverManager.getDriver (), 30);
    }

    public WebElement backBtn () {
        return this.driverManager.getDriver ()
            .findElement (AppiumBy.accessibilityId ("Back"));
    }

    public WebElement banner () {
        return this.wait.until (ExpectedConditions.presenceOfElementLocated (AppiumBy.accessibilityId ("banner")));
    }

    public void navigateToHomePage () {
        clickOn (backBtn ());
    }

}
