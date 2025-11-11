//package com.demoqa.base;
//
//import com.demoqa.utils.ConfigLoader;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import java.time.Duration;
//
//public class DriverFactory {
//    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
//
//    public static void initDriver(String browser) {
//        try {
//            WebDriverManager.chromedriver().setup();
//            ChromeOptions options = new ChromeOptions();
//
//            // ✅ Common stable flags
//            options.addArguments("--remote-allow-origins=*");
//            options.addArguments("--disable-dev-shm-usage");
//            options.addArguments("--no-sandbox");
//            options.addArguments("--disable-gpu");
//            options.addArguments("--start-maximized");
//
//            // ✅ Headless toggle from config.properties
//            boolean headless = Boolean.parseBoolean(ConfigLoader.get("headless"));
//            if (headless) {
//                options.addArguments("--headless=new");
//                System.out.println("[INFO] Running in headless mode");
//            } else {
//                System.out.println("[INFO] Running with visible browser");
//            }
//
//            // ✅ Initialize driver
//            tlDriver.set(new ChromeDriver(options));
//            getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
//            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static WebDriver getDriver() {
//        return tlDriver.get();
//    }
//
//    public static void quitDriver() {
//        if (getDriver() != null) {
//            getDriver().quit();
//            tlDriver.remove();
//        }
//    }
//}

//package com.demoqa.base;
//
//import com.demoqa.utils.ConfigLoader;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//import java.time.Duration;
//
//public class DriverFactory {
//
//    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
//
//    public static void initDriver(String browser) {
//        try {
//            WebDriverManager.chromedriver().setup();
//
//            ChromeOptions options = new ChromeOptions();
//
//            // ✅ Stable Chrome flags
//            options.addArguments("--remote-allow-origins=*");
//            options.addArguments("--disable-dev-shm-usage");
//            options.addArguments("--no-sandbox");
//            options.addArguments("--disable-gpu");
//            options.addArguments("--start-maximized");
//
//            // ✅ Cloudflare bypass tweaks
//            options.addArguments("--disable-blink-features=AutomationControlled");
//            options.addArguments("--disable-infobars");
//            options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
//                    + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");
//
//            // ✅ Headless toggle from config.properties
//            boolean headless = Boolean.parseBoolean(ConfigLoader.get("headless"));
//            System.out.println("⚙️ Headless flag loaded as: " + headless);
//            if (headless) {
//                options.addArguments("--headless=new");
//                System.out.println("[INFO] Running in headless mode");
//            } else {
//                System.out.println("[INFO] Running with visible browser");
//            }
//
//            // ✅ Initialize driver
//            tlDriver.set(new ChromeDriver(options));
//
//            // ✅ Timeouts
//            getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
//            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static WebDriver getDriver() {
//        return tlDriver.get();
//    }
//
//    public static void quitDriver() {
//        if (getDriver() != null) {
//            getDriver().quit();
//            tlDriver.remove();
//        }
//    }
//}

package com.demoqa.base;

import com.demoqa.utils.ConfigLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static void initDriver(String browser) {
        try {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--disable-infobars");
            options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");

            boolean headless = Boolean.parseBoolean(ConfigLoader.get("headless"));
            System.out.println("⚙️ Headless flag loaded as: " + headless);
            if (headless) {
                options.addArguments("--headless=new");
                System.out.println("[INFO] Running in headless mode");
            } else {
                System.out.println("[INFO] Running with visible browser");
            }

            tlDriver.set(new ChromeDriver(options));
            getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        try {
            if (getDriver() != null) {
                getDriver().quit();
                tlDriver.remove();
                System.out.println("🧹 Browser closed and driver removed successfully");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error while quitting driver: " + e.getMessage());
        }
    }
}