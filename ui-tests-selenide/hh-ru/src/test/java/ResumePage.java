import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ResumePage {
    private final SelenideElement gender = $x("//span[@data-qa='resume-personal-gender']");
    private final SelenideElement age = $x("//span[@data-qa='resume-personal-age']/span");
    private final SelenideElement liveString = $x("//span[@data-qa='resume-personal-address']/ancestor::p");
    private final SelenideElement confirmedNumber = $x("//div[@data-qa='resume-contacts-phone']//span[1]");
    private final SelenideElement city = $x("//span[@data-qa='resume-personal-address']");


    public ResumePage(String url) {
        Selenide.open(url);
    }


    //Пол кандидата
      public String getGenderEasy(){
        String genderValue = gender.getText();
        if(genderValue.equals("Мужчина")){
            return "М";
        }
        return "Ж";
    }
    //Пол кандидата через тернарный оператор
    public String getGenderHard(){
        return gender.getText().equals("Мужчина") ? "М" : "Ж";
    }


     // Регулярное выражение "[^0-9]" говорит о том, что нужно исключить все, что не начинается с цифры
     // Можно реализовать через регулярное выражение "\\D+" тем самым исключить все символы из строки
    public int getAge(){
        return Integer.parseInt(age.getText().replaceAll("[^0-9]",""));
    }






}
