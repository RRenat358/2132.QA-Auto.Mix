package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * Базовый класс для инициализации селенида
 */
abstract public class BaseSelenideTest {


    public void setUp(){
//        WebDriverManager.chromedriver().setup();
//        Configuration.driverManagerEnabled = true;
        Configuration.webdriverLogsEnabled = true;
        Configuration.browser = "chrome";
//        Configuration.browserSize = "1280x720";  //HD. 720p
//        Configuration.browserSize = "1366x768";  //FWXGA
//        Configuration.browserSize = "1600x900";  //HD+
//        Configuration.browserSize = "1920x1080"; //Full HD. 1080p
//        Configuration.browserSize = "2560x1440"; //QHD. 1440p
        Configuration.browserSize = "1280x1280";   //custom
        Configuration.headless = false;
//        Configuration.headless = true;
//        Configuration.browserVersion = "116";
//        Configuration.pageLoadStrategy = "normal";
        Configuration.pageLoadStrategy = "eager";
//        Configuration.pageLoadStrategy = "none";
//        Configuration.pageLoadTimeout = 5000;
    }


    @BeforeEach
    public void init(){
        setUp();
    }


    @AfterEach
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
