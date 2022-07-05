package io.github.mfaisalkhatri.mobileautomation.pages.ios;

import static io.github.mfaisalkhatri.utilities.Helper.clickOn;
import static io.github.mfaisalkhatri.utilities.Helper.waitForsomeTime;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.github.mfaisalkhatri.drivers.DriverManager;


public class BrowserPage {

    private DriverManager driverManager;

    public BrowserPage (final DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public MobileElement searchBox () {
        return driverManager.getDriver ()
            .findElement (MobileBy.AccessibilityId ("url"));
    }

    public void searchFor (String url) {
        searchBox ().sendKeys (url);
        clickOn (findBtn ());
        waitForsomeTime ();
    }

    public MobileElement findBtn () {
        return driverManager.getDriver ()
            .findElement (MobileBy.AccessibilityId ("Find"));
    }

    public void navigateToHomePage () {
        driverManager.getDriver ()
            .navigate ()
            .back ();
    }

}
