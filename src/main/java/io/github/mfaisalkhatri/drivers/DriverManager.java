package io.github.mfaisalkhatri.drivers;

import static java.text.MessageFormat.format;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    private static final ThreadLocal<AppiumDriver<MobileElement>> DRIVER = new ThreadLocal<> ();

    private static final String LT_USERNAME     = System.getenv ("username");
    private static final String LT_ACCESS_TOKEN = System.getenv ("token");
    private static final String GRID_URL        = "@mobile-hub.lambdatest.com/wd/hub";
    private              int    capsIndex;

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

    private void setupDriverTimeouts () {
        getDriver ().manage ()
            .timeouts ()
            .implicitlyWait (30, TimeUnit.SECONDS);
    }

    private DesiredCapabilities capabilities () {
        DesiredCapabilities capabilities = new DesiredCapabilities ();
        try (
            FileInputStream in = new FileInputStream (getClass ().getClassLoader ()
                .getResource ("parallel.config.json")
                .getPath ())) {

            ObjectMapper objectMapper = new ObjectMapper ();
            JsonNode jsonNode = objectMapper.readValue (in, JsonNode.class);

            JsonNode deviceInfoNode = jsonNode.get ("deviceAndBuildCaps")
                .get (capsIndex);

            deviceInfoNode.fields ()
                .forEachRemaining (entry -> {
                    String fieldName = entry.getKey ();
                    String fieldValue = entry.getValue ()
                        .asText ();
                    capabilities.setCapability (fieldName, fieldValue);
                });

            JsonNode capabilitiesNode = jsonNode.get ("commoncapabilities");
            capabilitiesNode.fields ()
                .forEachRemaining (capsEntry -> {
                    String commonCapsfieldName = capsEntry.getKey ();
                    String commonCapsfieldValue = capsEntry.getValue ()
                        .asText ();
                    capabilities.setCapability (commonCapsfieldName, commonCapsfieldValue);
                });
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return capabilities;
    }

}
