package io.github.mfaisalkhatri.mobileautomation.tests;

import io.github.mfaisalkhatri.drivers.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseTest {

    protected DriverManager driverManager;

    @Parameters({"capabilityIndex"})
    @BeforeClass(alwaysRun = true)
    public void setupTest (int capabilityIndex) {
        driverManager =  DriverManager.builder ().capsIndex (capabilityIndex)
            .build ().createRemoteDriver ();

    }
    @AfterClass(alwaysRun = true)
    public void tearDown () {
        driverManager.quitDriver ();
    }
}
