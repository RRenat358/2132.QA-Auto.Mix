package core;

import org.openqa.selenium.WebDriver;

abstract public class BaseSeleniumSetDriver {

    protected static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

}
