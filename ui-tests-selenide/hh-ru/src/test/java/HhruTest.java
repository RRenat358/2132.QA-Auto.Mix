import core.BaseSelenideTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HhruTest extends BaseSelenideTest {

    private final static String URL_RESUME = "https://hh.ru/resume/e0025611ff0c2c54740039ed1f524d33484b7a";


    /**
     * Получает атрибуты кандидата через HashMap, сравнивает актуальный и ожидаемый результат
     */
    @Test
    public void checkAttributesMap(){
        ResumePage resumePage = new ResumePage(URL_RESUME);
        //создаем карту ключ-значение с ожидаемыми данными
        Map<String,Object> expectedAttributes = new HashMap<>();
        expectedAttributes.put(ResumePage.GENDER,"М");
        expectedAttributes.put(ResumePage.AGE, 25);
        expectedAttributes.put(ResumePage.CITY, "Санкт-Петербург");
        expectedAttributes.put(ResumePage.RELOCATE, false);
        expectedAttributes.put(ResumePage.CONFIRMED_PHONE, true);

        //получаем карту ключ-значение с актуальными данными
        Map<String,Object> actualAttributes = resumePage.getAttributes();

        //сравниваем ожидаемый и актуальный результат
        Assertions.assertEquals(expectedAttributes,actualAttributes);
    }




}
