package io.github.mfaisalkhatri.mobileautomation.pages.android;

import java.time.Duration;

import io.github.mfaisalkhatri.mobileautomation.drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GeoLocationPage {

    private final DriverManager driverManager;
    private final WebDriverWait wait;

    public GeoLocationPage (final DriverManager driverManager) {
        this.driverManager = driverManager;
        this.wait = new WebDriverWait (driverManager.getDriver (), Duration.ofSeconds (30));
    }

    public WebElement content () {
        return this.wait.until (ExpectedConditions.presenceOfElementLocated (By.id ("android:id/content")));
    }

    public void navigateToHomePage () {
        this.driverManager.getDriver ()
            .navigate ()
            .back ();
    }

}
