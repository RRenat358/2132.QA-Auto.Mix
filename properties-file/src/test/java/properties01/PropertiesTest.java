package properties01;

import core.BaseSelenideTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PropertiesTest extends BaseSelenideTest {

    @Test
    public void readProperties() throws IOException {

        System.getProperties().load(ClassLoader.getSystemResourceAsStream("app01.properties"));

        String urlFromProp = System.getProperty("url");

        System.out.println("================================\n");
        System.out.println(urlFromProp);


        System.out.println("\n================================\n");
    }


}
