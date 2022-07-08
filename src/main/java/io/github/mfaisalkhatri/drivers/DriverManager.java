package io.github.mfaisalkhatri.drivers;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import lombok.Builder;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.DesiredCapabilities;

@Builder
public class DriverManager {

    private static final ThreadLocal<AppiumDriver<MobileElement>> DRIVER          = new ThreadLocal<> ();
    private static final String                                   GRID_URL        = "@mobile-hub.lambdatest.com/wd/hub";
    private static final String                                   LT_ACCESS_TOKEN = System.getenv ("token");
    private static final String                                   LT_USERNAME     = System.getenv ("username");
    private              String                                   deviceId;

    @SneakyThrows
    public DriverManager createRemoteDriver () {
        System.out.println (capabilities ());
        DRIVER.set (new AppiumDriver<> (new URL (format ("https://{0}:{1}{2}", LT_USERNAME, LT_ACCESS_TOKEN, GRID_URL)),
            capabilities ()));

        setupDriverTimeouts ();
        return this;
    }

    @SuppressWarnings ("unchecked")
    public <D extends AppiumDriver<MobileElement>> D getDriver () {
        if (null == DRIVER.get ()) {
            createRemoteDriver ();
        }
        return (D) DRIVER.get ();
    }

    public void quitDriver () {
        if (null != DRIVER.get ()) {
            getDriver ().quit ();
            DRIVER.remove ();
        }
    }

    private DesiredCapabilities capabilities () {
        final DesiredCapabilities capabilities = new DesiredCapabilities ();
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
                    device.fields ()
                        .forEachRemaining (capability -> {
                            if (!capability.getKey ()
                                .equals ("id")) {
                                capabilities.setCapability (capability.getKey (), capability.getValue ()
                                    .asText ());
                            }
                        });
                }
            });

            final JsonNode capabilitiesNode = jsonNode.get ("commonCapabilities");
            capabilitiesNode.fields ()
                .forEachRemaining (capsEntry -> {
                    final String commonCapsfieldName = capsEntry.getKey ();
                    final String commonCapsfieldValue = capsEntry.getValue ()
                        .asText ();
                    capabilities.setCapability (commonCapsfieldName, commonCapsfieldValue);
                });
        } catch (final IOException e) {
            e.printStackTrace ();
        }
        return capabilities;
    }

    private void setupDriverTimeouts () {
        getDriver ().manage ()
            .timeouts ()
            .implicitlyWait (30, TimeUnit.SECONDS);
    }

}
