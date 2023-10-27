package properties01;

import core.BaseSelenideTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PropertiesTest extends BaseSelenideTest {

    // Вариант 01
    // Чтение из файла напрямую. Может вылететь эксепшен
    @Test
    public void readProperties() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("app01.properties"));
        String urlFromProp = System.getProperty("url");

        System.out.println("\n================================\n");
        System.out.println(urlFromProp);
        System.out.println("\n================================\n");
    }


    // Вариант 02
    // Чтение из специального файла конфигураций,
    // в котором в свою очередь чтение из файла
    @Test
    public void readFromConf(){
        System.out.println("\n================================\n");
        String urlFromConf = App02ConfigProvider.URL;
        System.out.println(urlFromConf);
        Boolean isDemoAdmin = App02ConfigProvider.IS_DEMO_ADMIN;
        System.out.println(isDemoAdmin);

        System.out.println("\n================================\n");
        if(App02ConfigProvider.readConfig().getBoolean("usersParams.admin.isAdmin")){
            System.out.println("Админ действительно админ");
        } else {
            System.out.println("12");
        }

        System.out.println("\n================================\n");
    }




}
