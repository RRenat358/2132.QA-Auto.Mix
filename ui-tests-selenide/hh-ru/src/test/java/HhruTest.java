import core.BaseSelenideTest;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.apache.logging.log4j.Level.ALL;
import static org.apache.logging.log4j.Level.INFO;

public class HhruTest extends BaseSelenideTest {

    private static final Logger logger = LogManager.getLogger();

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
        expectedAttributes.put(ResumePage.AGE, 41);
        expectedAttributes.put(ResumePage.CITY, "Москва");
        expectedAttributes.put(ResumePage.RELOCATE, true);
//        expectedAttributes.put(ResumePage.CONFIRMED_PHONE, true);
        expectedAttributes.put(ResumePage.CONFIRMED_PHONE, false);

        //получаем карту ключ-значение с актуальными данными
        Map<String,Object> actualAttributes = resumePage.getAttributes();

        //сравниваем ожидаемый и актуальный результат
        Assertions.assertEquals(expectedAttributes,actualAttributes);
    }

    @Test
    public void checkAttributesClass(){
        ResumePage resumePage = new ResumePage(URL_RESUME);
        //создаем экземпляр класса с ожидаемыми данными через конструктор
        ResumePage2 expectedResume = new ResumePage2("М", 41,"Москва", true,false);

        //получаем экземпляр класса с актуальными данными через конструктор
        ResumePage2 actualResume = new ResumePage2(
                resumePage.getGenderEasy(),
                resumePage.getAge(),
                resumePage.getCityEasy(),
                resumePage.isReadyToRelocate(),
                resumePage.isPhoneConfirmed()
        );

        //1 способ сравнения классов
        Assertions.assertTrue(EqualsBuilder.reflectionEquals(expectedResume,actualResume));




        //2 способ сравнения отдельных переменных в классе
        Assertions.assertEquals(expectedResume.getGender(), actualResume.getGender());
        Assertions.assertEquals(expectedResume.getAge(), actualResume.getAge());
        Assertions.assertEquals(expectedResume.getCity(), actualResume.getCity());
        Assertions.assertEquals(expectedResume.isReadyToRelocate(), actualResume.isReadyToRelocate());
        Assertions.assertEquals(expectedResume.isNumberConfirmed(), actualResume.isNumberConfirmed());
    }




}
