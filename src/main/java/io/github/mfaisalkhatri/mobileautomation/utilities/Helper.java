package io.github.mfaisalkhatri.mobileautomation.utilities;

import io.github.mfaisalkhatri.mobileautomation.drivers.DriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.WebElement;

public class Helper {

    public static void clickOn (final WebElement element) {
        element.click ();
    }

    @SneakyThrows
    public static void waitForSomeTime () {
        Thread.sleep (2000);
    }

    DriverManager driverManager;

    public Helper (final DriverManager driverManager) {
        this.driverManager = driverManager;
    }

}
