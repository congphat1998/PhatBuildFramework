package org.example.core.drivers;

import org.example.constant.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.Driver;
import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public synchronized static void getLocalDriver(String browser) throws Exception{
        WebDriver tDriver;
        switch (browser.toLowerCase()){
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                tDriver = new ChromeDriver(chromeOptions);
                System.out.println("created driver");
                break;
            case "firefox":
                tDriver = new FirefoxDriver();
                break;
            default:
                throw new Exception("Wrong browser name, please check again !");
        }
        tDriver.manage().window().maximize();
        tDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_TIME));
        tDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.WAIT_TIME));
        tDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        setDriver(tDriver);
    }
    public static WebDriver getDriver(){
        return driver.get();
    }
    public static void setDriver(WebDriver driver){
        DriverManager.driver.set(driver);
    }
    public static void quitDriver(){
        if(getDriver() != null){
            getDriver().quit();
        }
    }
}
