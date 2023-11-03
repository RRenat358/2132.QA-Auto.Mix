package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

abstract public class BaseSelenium {

    //    protected WebDriver driver;
    protected WebDriver driver = new ChromeDriver();

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();

//        System.setProperty("webdriver.http.factory", "jdk-http-client");

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-debugging-port=9222");
//        options.addArguments("--remote-allow-origins=*");
//        driver = new ChromeDriver(options);

//        driver = new ChromeDriver(); //?
//        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1280,1280));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        BaseSeleniumSetDriver.setDriver(driver);
    }

    @AfterEach
    public void shutdown() {
        driver.quit(); //Завершает работу этого драйвера, закрывая все связанные окна.
        driver.close(); //Закройте текущее окно, выйдя из браузера, если это последнее открытое окно.
    }


}
