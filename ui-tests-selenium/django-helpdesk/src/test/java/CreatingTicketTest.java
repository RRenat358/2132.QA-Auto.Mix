
import core.BaseSelenium;
import configuration.TestValues;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import configuration.ConfigProvider;
import webPage.MainPage;
import webPage.NewTicketPage;

import static helpers.StringModifier.getUniqueString;

public class CreatingTicketTest extends BaseSelenium {


    //вариант 2
    @Test
    public void checkTicket(){
        String title = getUniqueString(TestValues.TEST_TITLE);
        NewTicketPage newTicketPage = new MainPage().createTicket(title, TestValues.TEST_BODY, TestValues.TEST_EMAIL)
                .openLoginPage()
                .auth(ConfigProvider.DEMO_LOGIN, ConfigProvider.DEMO_PASSWORD)
                .findTicket(title);
        Assertions.assertTrue(newTicketPage.getTitle().contains(title));
        Assertions.assertEquals(newTicketPage.getBody(), TestValues.TEST_BODY);
        Assertions.assertEquals(newTicketPage.getEmail(), TestValues.TEST_EMAIL);
    }




}
