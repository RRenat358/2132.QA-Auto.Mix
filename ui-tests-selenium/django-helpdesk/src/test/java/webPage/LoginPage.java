package webPage;

import core.BaseSelenium;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseSelenium {
    @FindBy (id = "username")
    private WebElement loginField;

    @FindBy (id = "password")
    private WebElement passwordField;

    public LoginPage() {
        PageFactory.initElements(driver,this);
    }

    public ListingTicketsPage auth(String login, String password){
        loginField.sendKeys(login);
        passwordField.sendKeys(password, Keys.ENTER);
        return new ListingTicketsPage();
    }

}
