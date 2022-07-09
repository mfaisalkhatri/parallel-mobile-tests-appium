package io.github.mfaisalkhatri.mobileautomation.tests;

import static io.github.mfaisalkhatri.utilities.Helper.clickOn;
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

    @BeforeClass
    public void iosSetupTest () {
        homePage = new HomePage (driverManager);
    }

    @Test
    public void textTests () {
        assertEquals (homePage.getText (), "Hello! Welcome to lambdatest Sample App called Proverbial");
        clickOn (homePage.textBtn ());
        assertEquals (homePage.getText (), "Proverbial");
    }
    
    @Test
    public void notificationTest () {
        clickOn (homePage.notificationBtn ());
        assertTrue (homePage.notificationBar ()
            .isDisplayed ());
    }

    @Test
    public void toastMessageTest () {
        clickOn (homePage.toastBtn ());
    }

    @Test
    public void geoLocationTest () {
        GeoLocationPage geoLocationPage = new GeoLocationPage (driverManager);
        clickOn (homePage.geoLocationBtn ());
        assertTrue (geoLocationPage.banner ()
            .isDisplayed ());
        geoLocationPage.navigateToHomePage ();
    }

    @Test
    public void speedTestPageTest () {
        SpeedTestPage speedTestPage = new SpeedTestPage (driverManager);
        clickOn (homePage.speedtTestBtn ());
        assertEquals (speedTestPage.headerText (), "Speedtest");
        speedTestPage.navigateToHomePage ();
    }

    @Test
    public void browserTest () {
        BrowserPage browserPage = new BrowserPage (driverManager);
        clickOn (homePage.browserMenu ());
        browserPage.searchFor ("https://lambdatest.com");
    }
}
