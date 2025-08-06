package io.github.mfaisalkhatri.mobileautomation.tests;

import static io.github.mfaisalkhatri.mobileautomation.utilities.Helper.clickOn;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.github.mfaisalkhatri.mobileautomation.pages.android.BrowserPage;
import io.github.mfaisalkhatri.mobileautomation.pages.android.GeoLocationPage;
import io.github.mfaisalkhatri.mobileautomation.pages.android.HomePage;
import io.github.mfaisalkhatri.mobileautomation.pages.android.SpeedTestPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AndroidTests extends BaseTest {

    private HomePage homePage;

    @BeforeClass
    public void androidSetupTest () {
        this.homePage = new HomePage (this.driverManager);
    }

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
        assertTrue (geoLocationPage.content ()
            .isDisplayed ());
        geoLocationPage.navigateToHomePage ();
    }

    @Test
    public void notificationTest () {
        clickOn (this.homePage.notificationBtn ());
        assertTrue (this.homePage.notificationBar ()
            .isDisplayed ());
        clickOn (this.homePage.notificationBar ());
    }

    @Test
    public void speedTestPageTest () {
        final SpeedTestPage speedTestPage = new SpeedTestPage (this.driverManager);
        clickOn (this.homePage.speedtTestBtn ());
        assertTrue (speedTestPage.headerText ()
            .isDisplayed ());
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
        assertEquals (this.homePage.toastMessage (), "Toast should be visible");
    }
}