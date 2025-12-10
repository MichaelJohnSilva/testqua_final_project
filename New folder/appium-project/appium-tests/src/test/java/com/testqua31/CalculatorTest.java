package com.testqua31;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class CalculatorTest {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability("appPackage", "apps.r.calculator");
        caps.setCapability("appActivity", ".CalculatorActivity");
        caps.setCapability("noReset", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4726/"), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testAddition() {
        // Tap 2
        driver.findElementById("apps.r.calculator:id/digit_2").click();
        // Tap +
        driver.findElementById("apps.r.calculator:id/op_add").click();
        // Tap 3
        driver.findElementById("apps.r.calculator:id/digit_3").click();
        // Tap =
        driver.findElementById("apps.r.calculator:id/eq").click();

        // Get result
        String result = driver.findElementById("apps.r.calculator:id/result").getText().trim();
        Assert.assertEquals(result, "5", "Addition result is incorrect");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
