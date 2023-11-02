package webPage;

import core.BaseSelenium;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ListingTicketsPage extends BaseSelenium {
    @FindBy(id = "search_query")
    private WebElement searchField;
    @FindBy(xpath = "//div[@class='tickettitle']")
    private WebElement ticket;

    public ListingTicketsPage() {
        PageFactory.initElements(driver,this);
    }

    public NewTicketPage findTicket(String str){
        searchField.sendKeys(str, Keys.ENTER);
        ticket.click();
        return new NewTicketPage();
    }
}
