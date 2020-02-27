package base;

import manager.DriverManager;
import manager.DriverManagerFactory;
import manager.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Created by Gadotey on 2/25/2020
 */
public class BaseUtil {
    public WebDriver driver;
    DriverManager driverManager;


    @BeforeMethod
    @Parameters("browserName")
    public void setUp(@Optional String browserName) {
        getLocalDriver(browserName);
        //driver.manage().window().maximize();
        driver.navigate().to("https://google.com");
    }

    public WebDriver getLocalDriver(String browserName) {
        if (browserName.equalsIgnoreCase("Chrome")) {
            driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
            driver = driverManager.getDriver();
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            driverManager = DriverManagerFactory.getManager(DriverType.FIREFOX);
            driver = driverManager.getDriver();
        } else {
            //This exception will be displayed on the screen if the driver is not found.
            throw new RuntimeException("No Such Driver Found!");
        }
        return driver;
    }

    @AfterMethod
    public void tearDown() {
        driverManager.quitDriver();
    }

}