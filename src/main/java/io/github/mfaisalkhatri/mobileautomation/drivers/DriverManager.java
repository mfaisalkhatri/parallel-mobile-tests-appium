package io.github.mfaisalkhatri.mobileautomation.drivers;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import lombok.Builder;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

@Builder
public class DriverManager {

    private static final ThreadLocal<AppiumDriver> DRIVER          = new ThreadLocal<> ();
    private static final String                    GRID_URL        = "@mobile-hub.lambdatest.com/wd/hub";
    private static final Logger                    LOGGER          = LogManager.getLogger ("DriverManager.class");
    private static final String                    LT_ACCESS_TOKEN = System.getenv ("LT_ACCESS_KEY");
    private static final String                    LT_USERNAME     = System.getenv ("LT_USERNAME");
    private              String                    deviceId;

    @SneakyThrows
    public DriverManager createRemoteDriver () {
        LOGGER.info ("Creating the driver...");
        LOGGER.info ("Setting the capabilities..." + capabilities ());
        DRIVER.set (new AppiumDriver (new URL (
            format ("https://{0}:{1}{2}", DriverManager.LT_USERNAME, DriverManager.LT_ACCESS_TOKEN,
                DriverManager.GRID_URL)), capabilities ()));
        setupDriverTimeouts ();
        return this;
    }

    @SuppressWarnings ("unchecked")
    public <D extends AppiumDriver> D getDriver () {
        if (null == DriverManager.DRIVER.get ()) {
            createRemoteDriver ();
        }
        return (D) DriverManager.DRIVER.get ();
    }

    public void quitDriver () {
        if (null != DriverManager.DRIVER.get ()) {
            LOGGER.info ("Closing the driver...");
            getDriver ().quit ();
            DriverManager.DRIVER.remove ();
        }
    }

    private DesiredCapabilities capabilities () {
        final DesiredCapabilities capabilities = new DesiredCapabilities ();
        final HashMap<String, Object> ltOptions = new HashMap<> ();
        try (
            final var in = new FileInputStream (requireNonNull (getClass ().getClassLoader ()
                .getResource ("parallel.config.json")).getPath ())) {
            final var objectMapper = new ObjectMapper ();
            final JsonNode jsonNode = objectMapper.readValue (in, JsonNode.class);
            final var devices = jsonNode.get ("deviceAndBuildCaps")
                .elements ();
            devices.forEachRemaining (device -> {
                if (device.get ("id")
                    .asText ()
                    .equals (this.deviceId)) {
                    device.properties ()
                        .forEach (capability -> {
                            if (!capability.getKey ()
                                .equals ("id")) {
                                ltOptions.put (capability.getKey (), capability.getValue ()
                                    .asText ());
                            }
                        });
                }
            });
            final JsonNode capabilitiesNode = jsonNode.get ("commonCapabilities");
            capabilitiesNode.properties ()
                .forEach (capsEntry -> ltOptions.put (capsEntry.getKey (), capsEntry.getValue ()
                    .asText ()));
        } catch (final IOException e) {
            LOGGER.error ("Error reading the config json file", e);
        }
        capabilities.setCapability ("lt:options", ltOptions);
        return capabilities;
    }

    private void setupDriverTimeouts () {
        getDriver ().manage ()
            .timeouts ()
            .implicitlyWait (Duration.ofSeconds (30));
    }
}