package io.github.mfaisalkhatri.mobileautomation.tests;

import io.github.mfaisalkhatri.drivers.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected DriverManager driverManager;

    @Parameters ("deviceId")
    @BeforeClass (alwaysRun = true)
    public void setupTest (final String deviceId) {
        this.driverManager = DriverManager.builder ()
            .deviceId (deviceId)
            .build ()
            .createRemoteDriver ();

    }

    @AfterClass (alwaysRun = true)
    public void tearDown () {
        this.driverManager.quitDriver ();
    }
}
