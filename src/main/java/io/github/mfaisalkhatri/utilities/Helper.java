package io.github.mfaisalkhatri.utilities;

import io.appium.java_client.MobileElement;
import io.github.mfaisalkhatri.drivers.DriverManager;
import lombok.SneakyThrows;

public class Helper {

    DriverManager driverManager;

    public Helper (final DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public static void clickOn(MobileElement element) {
        element.click ();
    }

    @SneakyThrows
    public static void waitForsomeTime () {
        Thread.sleep (2000);
    }
}
