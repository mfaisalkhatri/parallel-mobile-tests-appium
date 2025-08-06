package io.github.mfaisalkhatri.mobileautomation.tests;

import static io.github.mfaisalkhatri.mobileautomation.utilities.Helper.clickOn;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.github.mfaisalkhatri.mobileautomation.pages.ios.BrowserPage;
import io.github.mfaisalkhatri.mobileautomation.pages.ios.GeoLocationPage;
import io.github.mfaisalkhatri.mobileautomation.pages.ios.HomePage;
import io.github.mfaisalkhatri.mobileautomation.pages.ios.SpeedTestPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IOSTests extends BaseTest {

    private HomePage homePage;

    @Test
    public void browserTest () {
        final BrowserPage browserPage = new BrowserPage (this.driverManager);
        clickOn (this.homePage.browserMenu ());
        browserPage.searchFor ("https://lambdatest.com");
    }

    @Test
    public void geoLocationTest () {
        final GeoLocationPage geoLocationPage = new GeoLocationPage (this.driverManager);
        clickOn (this.homePage.geoLocationBtn ());
        assertTrue (geoLocationPage.banner ()
            .isDisplayed ());
        geoLocationPage.navigateToHomePage ();
    }

    @BeforeClass
    public void iosSetupTest () {
        this.homePage = new HomePage (this.driverManager);
    }

    @Test
    public void notificationTest () {
        clickOn (this.homePage.notificationBtn ());
        assertTrue (this.homePage.notificationBar ()
            .isDisplayed ());
    }

    @Test
    public void speedTestPageTest () {
        final SpeedTestPage speedTestPage = new SpeedTestPage (this.driverManager);
        clickOn (this.homePage.speedtTestBtn ());
        assertEquals (speedTestPage.headerText (), "Speedtest");
        speedTestPage.navigateToHomePage ();
    }

    @Test
    public void textTests () {
        assertEquals (this.homePage.getText (), "Hello! Welcome to lambdatest Sample App called Proverbial");
        clickOn (this.homePage.textBtn ());
        assertEquals (this.homePage.getText (), "Proverbial");
    }

    @Test
    public void toastMessageTest () {
        clickOn (this.homePage.toastBtn ());
    }
}
