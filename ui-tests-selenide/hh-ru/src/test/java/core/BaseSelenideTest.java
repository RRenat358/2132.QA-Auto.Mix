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

    /**
     * Инициализация selenide с настройками
     */
    public void setUp(){
//        WebDriverManager.chromedriver().setup();
//        Configuration.driverManagerEnabled = true;
        Configuration.webdriverLogsEnabled = true;
        Configuration.browser = "chrome";
//        Configuration.browserSize = "1920x1080"; // Full HD. 1080p
        Configuration.browserSize = "1280x1280"; // Full HD. 1080p
        Configuration.headless = false;
//        Configuration.headless = true;
//        Configuration.browserVersion = "116";
//        Configuration.pageLoadStrategy = "normal";
        Configuration.pageLoadStrategy = "eager";
//        Configuration.pageLoadStrategy = "none";
//        Configuration.pageLoadTimeout = 5000;
    }

    /**
     * Выполнение метода перед каждым запуском тестов
     */
    @BeforeEach
    public void init(){
        setUp();
    }

    /**
     * Выполнение метода после каждого закрытия тестов
     */
    @AfterEach
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
