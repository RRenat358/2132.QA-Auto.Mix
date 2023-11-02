package core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

abstract public class BaseSelenium {

    protected WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280,1280));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        BaseSeleniumPage.setDriver(driver);
    }

    @AfterEach
    public void shutdown() {
        driver.close(); //Закройте текущее окно, выйдя из браузера, если это последнее открытое окно.
        driver.quit(); //Завершает работу этого драйвера, закрывая все связанные окна.
    }


}
