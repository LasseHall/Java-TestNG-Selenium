package com.yourcompany.Tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;

/**
 * Simple TestNG test which demonstrates being instantiated via a DataProvider in order to supply multiple browser combinations.
 *
 * @author Neil Manvar
 */
public class TestBase  {

    public String buildTag = System.getenv("BUILD_TAG");

    public String username = System.getenv("SAUCE_USERNAME");

    public String accesskey = System.getenv("SAUCE_ACCESS_KEY");

    public String endpoint = System.getenv("DC_ENDPOINT");

    /**
     * ThreadLocal variable which contains the  {@link WebDriver} instance which is used to perform browser interactions with.
     */
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    /**
     * ThreadLocal variable which contains the Sauce Job Id.
     */
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();

    private ThreadLocal<Boolean> isMobile = new ThreadLocal<Boolean>();

    /**
     * DataProvider that explicitly sets the browser combinations to be used.
     *
     * @param testMethod
     * @return Two dimensional array of objects with browser, version, and platform information
     */
    @DataProvider(name = "hardCodedBrowsers", parallel = true)
    public static Object[][] sauceBrowserDataProvider(Method testMethod) {
        return new Object[][]{

                // Problematic old browser versions
                /*new Object[]{"chrome", "60", "Windows 10"},
                new Object[]{"chrome", "59", "Windows 10"},
                new Object[]{"chrome", "58", "Windows 10"},
                new Object[]{"chrome", "57", "Windows 10"},
                new Object[]{"chrome", "56", "Windows 10"},
                new Object[]{"chrome", "55", "Windows 10"},
                new Object[]{"chrome", "54", "Windows 10"},
                new Object[]{"chrome", "53", "Windows 10"},
                new Object[]{"chrome", "52", "Windows 10"},
                new Object[]{"chrome", "51", "Windows 10"},
                new Object[]{"chrome", "50", "Windows 10"},*/


                new Object[]{"MicrosoftEdge", "latest", "Windows 10"},
                new Object[]{"MicrosoftEdge", "latest-1", "Windows 10"},
                new Object[]{"MicrosoftEdge", "latest-2", "Windows 10"},
                new Object[]{"MicrosoftEdge", "latest-3", "Windows 10"},
                new Object[]{"MicrosoftEdge", "latest-4", "Windows 10"},
                new Object[]{"internet explorer", "latest", "Windows 10"},
                new Object[]{"firefox", "latest", "Windows 10"},
                new Object[]{"firefox", "latest-1", "Windows 10"},
                new Object[]{"firefox", "latest-2", "Windows 10"},
                new Object[]{"firefox", "latest-3", "Windows 10"},
                new Object[]{"firefox", "latest-4", "Windows 10"},
                new Object[]{"firefox", "latest-5", "Windows 10"},
                new Object[]{"firefox", "latest-6", "Windows 10"},
                new Object[]{"firefox", "latest-7", "Windows 10"},
                new Object[]{"firefox", "latest-8", "Windows 10"},
                new Object[]{"firefox", "latest-9", "Windows 10"},
                new Object[]{"firefox", "latest-10", "Windows 10"},
                new Object[]{"chrome", "latest", "Windows 10"},
                new Object[]{"chrome", "latest-1", "Windows 10"},
                new Object[]{"chrome", "latest-2", "Windows 10"},
                new Object[]{"chrome", "latest-3", "Windows 10"},
                new Object[]{"chrome", "latest-4", "Windows 10"},
                new Object[]{"chrome", "latest-5", "Windows 10"},
                new Object[]{"chrome", "latest-6", "Windows 10"},
                new Object[]{"chrome", "latest-7", "Windows 10"},
                new Object[]{"chrome", "latest-8", "Windows 10"},
                new Object[]{"chrome", "latest-9", "Windows 10"},
                new Object[]{"chrome", "latest-10", "Windows 10"},



                new Object[]{"internet explorer", "latest", "Windows 8.1"},
                new Object[]{"firefox", "latest", "Windows 8.1"},
                new Object[]{"firefox", "latest-1", "Windows 8.1"},
                new Object[]{"firefox", "latest-2", "Windows 8.1"},
                new Object[]{"firefox", "latest-3", "Windows 8.1"},
                new Object[]{"firefox", "latest-4", "Windows 8.1"},
                new Object[]{"firefox", "latest-5", "Windows 8.1"},
                new Object[]{"firefox", "latest-6", "Windows 8.1"},
                new Object[]{"firefox", "latest-7", "Windows 8.1"},
                new Object[]{"firefox", "latest-8", "Windows 8.1"},
                new Object[]{"firefox", "latest-9", "Windows 8.1"},
                new Object[]{"firefox", "latest-10", "Windows 8.1"},
                new Object[]{"chrome", "latest", "Windows 8.1"},
                new Object[]{"chrome", "latest-1", "Windows 8.1"},
                new Object[]{"chrome", "latest-2", "Windows 8.1"},
                new Object[]{"chrome", "latest-3", "Windows 8.1"},
                new Object[]{"chrome", "latest-4", "Windows 8.1"},
                new Object[]{"chrome", "latest-5", "Windows 8.1"},
                new Object[]{"chrome", "latest-6", "Windows 8.1"},
                new Object[]{"chrome", "latest-7", "Windows 8.1"},
                new Object[]{"chrome", "latest-8", "Windows 8.1"},
                new Object[]{"chrome", "latest-9", "Windows 8.1"},
                new Object[]{"chrome", "latest-10", "Windows 8.1"},

                new Object[]{"internet explorer", "latest", "Windows 8"},
                new Object[]{"firefox", "latest", "Windows 8"},
                new Object[]{"firefox", "latest-1", "Windows 8"},
                new Object[]{"firefox", "latest-2", "Windows 8"},
                new Object[]{"firefox", "latest-3", "Windows 8"},
                new Object[]{"firefox", "latest-4", "Windows 8"},
                new Object[]{"firefox", "latest-5", "Windows 8"},
                new Object[]{"firefox", "latest-6", "Windows 8"},
                new Object[]{"firefox", "latest-7", "Windows 8"},
                new Object[]{"firefox", "latest-8", "Windows 8"},
                new Object[]{"firefox", "latest-9", "Windows 8"},
                new Object[]{"firefox", "latest-10", "Windows 8"},
                new Object[]{"chrome", "latest", "Windows 8"},
                new Object[]{"chrome", "latest-1", "Windows 8"},
                new Object[]{"chrome", "latest-2", "Windows 8"},
                new Object[]{"chrome", "latest-3", "Windows 8"},
                new Object[]{"chrome", "latest-4", "Windows 8"},
                new Object[]{"chrome", "latest-5", "Windows 8"},
                new Object[]{"chrome", "latest-6", "Windows 8"},
                new Object[]{"chrome", "latest-7", "Windows 8"},
                new Object[]{"chrome", "latest-8", "Windows 8"},
                new Object[]{"chrome", "latest-9", "Windows 8"},
                new Object[]{"chrome", "latest-10", "Windows 8"},

                new Object[]{"internet explorer", "latest", "Windows 7"},
                new Object[]{"internet explorer", "latest-1", "Windows 7"},
                new Object[]{"internet explorer", "latest-2", "Windows 7"},
                new Object[]{"firefox", "latest", "Windows 7"},
                new Object[]{"firefox", "latest-1", "Windows 7"},
                new Object[]{"firefox", "latest-2", "Windows 7"},
                new Object[]{"firefox", "latest-3", "Windows 7"},
                new Object[]{"firefox", "latest-4", "Windows 7"},
                new Object[]{"firefox", "latest-5", "Windows 7"},
                new Object[]{"firefox", "latest-6", "Windows 7"},
                new Object[]{"firefox", "latest-7", "Windows 7"},
                new Object[]{"firefox", "latest-8", "Windows 7"},
                new Object[]{"firefox", "latest-9", "Windows 7"},
                new Object[]{"firefox", "latest-10", "Windows 7"},
                new Object[]{"chrome", "latest", "Windows 7"},
                new Object[]{"chrome", "latest-1", "Windows 7"},
                new Object[]{"chrome", "latest-2", "Windows 7"},
                new Object[]{"chrome", "latest-3", "Windows 7"},
                new Object[]{"chrome", "latest-4", "Windows 7"},
                new Object[]{"chrome", "latest-5", "Windows 7"},
                new Object[]{"chrome", "latest-6", "Windows 7"},
                new Object[]{"chrome", "latest-7", "Windows 7"},
                new Object[]{"chrome", "latest-8", "Windows 7"},
                new Object[]{"chrome", "latest-9", "Windows 7"},
                new Object[]{"chrome", "latest-10", "Windows 7"},

                new Object[]{"firefox", "latest", "Linux"},
                new Object[]{"firefox", "latest-1", "Linux"},
                new Object[]{"firefox", "latest-2", "Linux"},
                new Object[]{"firefox", "latest-3", "Linux"},
                new Object[]{"firefox", "latest-4", "Linux"},
                new Object[]{"firefox", "latest-5", "Linux"},
                new Object[]{"firefox", "latest-6", "Linux"},
                new Object[]{"firefox", "latest-7", "Linux"},
                new Object[]{"firefox", "latest-8", "Linux"},
                new Object[]{"firefox", "latest-9", "Linux"},
                new Object[]{"firefox", "latest-10", "Linux"},
                new Object[]{"chrome", "latest", "Linux"},
                new Object[]{"chrome", "latest-1", "Linux"},
                new Object[]{"chrome", "latest-2", "Linux"},
                new Object[]{"chrome", "latest-3", "Linux"},
                new Object[]{"chrome", "latest-4", "Linux"},
                new Object[]{"chrome", "latest-5", "Linux"},
                new Object[]{"chrome", "latest-6", "Linux"},
                new Object[]{"chrome", "latest-7", "Linux"},
                new Object[]{"chrome", "latest-8", "Linux"},
                new Object[]{"chrome", "latest-9", "Linux"},
                new Object[]{"chrome", "latest-10", "Linux"},

                new Object[]{"Chrome", "7.1", "Android GoogleAPI Emulator"},
                new Object[]{"Chrome", "7.0", "Android GoogleAPI Emulator"},
                new Object[]{"Chrome", "6.0", "Android GoogleAPI Emulator"},
                new Object[]{"Browser", "5.1", "Android GoogleAPI Emulator"},
                new Object[]{"Browser", "5.0", "Android GoogleAPI Emulator"},
                new Object[]{"Browser", "4.4", "Android GoogleAPI Emulator"},

                new Object[]{"Chrome", "6.0", "Android Emulator"},
                new Object[]{"Browser", "5.1", "Android Emulator"},
                new Object[]{"Browser", "5.0", "Android Emulator"},
                new Object[]{"Browser", "4.4", "Android Emulator"},

                new Object[]{"Chrome", "7.1", "Samsung Galaxy S9 WQHD GoogleAPI Emulator"},
                new Object[]{"Chrome", "7.0", "Samsung Galaxy S9 WQHD GoogleAPI Emulator"},

                new Object[]{"Chrome", "7.1", "Google Pixel GoogleAPI Emulator"},
                new Object[]{"Chrome", "7.0", "Google Pixel GoogleAPI Emulator"},


        };
    }

    /**
     * @return the {@link WebDriver} for the current thread
     */
    public WebDriver getWebDriver() {
        return webDriver.get();
    }

    /**
     *
     * @return the Sauce Job id for the current thread
     */
    public String getSessionId() {
        return sessionId.get();
    }

    public Boolean isMobile() {
        return isMobile.get();
    }

    /**
     * Constructs a new {@link RemoteWebDriver} instance which is configured to use the capabilities defined by the browser,
     * version and os parameters, and which is configured to run against ondemand.saucelabs.com, using
     * the username and access key fetched from Environment Variables.
     *
     * @param browser Represents the browser to be used as part of the test run.
     * @param version Represents the version of the browser to be used as part of the test run.
     * @param os Represents the operating system to be used as part of the test run.
     * @param methodName Represents the name of the test case that will be used to identify the test on Sauce.
     * @return
     * @throws MalformedURLException if an error occurs parsing the url
     */
    protected void createDriver(String browser, String version, String os, String methodName)
            throws MalformedURLException, UnexpectedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // set desired capabilities to launch appropriate browser on Sauce
        if (os.contains("Simulator") || os.contains("Emulator")) {
            // Emu/Sim
            isMobile.set(true);
            if (os.contains("Simulator")) {
                capabilities.setCapability("platformName", "iOS");
            } else {
                capabilities.setCapability("platformName", "Android");
            }
            capabilities.setCapability("deviceName", os);
            capabilities.setCapability("deviceOrientation", "portrait");
            capabilities.setCapability("appiumVersion", "1.9.1");
            capabilities.setCapability("platformVersion", version);
            capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        } else {
            isMobile.set(false);
            capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
            capabilities.setCapability(CapabilityType.VERSION, version);
            capabilities.setCapability(CapabilityType.PLATFORM, os);
        }
        capabilities.setCapability("name", methodName);
        capabilities.setCapability("commandTimeout", 60);
        capabilities.setCapability("idleTimeout", 300);
        capabilities.setCapability("extendedDebugging", true);

        if (buildTag != null) {
            capabilities.setCapability("build", buildTag);
        }

        // Launch remote browser and set it as the current thread
        webDriver.set(new RemoteWebDriver(
                new URL("https://" + username + ":" + accesskey + endpoint),
                capabilities));

        // set current sessionId
        String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        sessionId.set(id);
    }

    /**
     * Method that gets invoked after test.
     * Dumps browser log and
     * Closes the browser
     */
    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        if (!isMobile.get()) {
            ((JavascriptExecutor) webDriver.get()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        }
        System.out.println("SauceOnDemandSessionID=" + getSessionId() + " job-name=" + result.getName());
        webDriver.get().quit();
    }

    protected void annotate(String text) {
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce:context=" + text);
    }
}
