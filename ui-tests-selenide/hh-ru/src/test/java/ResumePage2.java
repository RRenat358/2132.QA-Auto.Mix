import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Класс для описания атрибутов кандидата из страницы с резюме на hh
 */

//@Getter
//@AllArgsConstructor
public class ResumePage2 {
    /**
     * Атрибуты для кандидата
     */
    private final String gender;
    private final String city;
    private final int age;
    private final boolean isNumberConfirmed;
    private final boolean isReadyToRelocate;


    public ResumePage2(String gender, String city, int age, boolean isNumberConfirmed, boolean isReadyToRelocate) {
        this.gender = gender;
        this.city = city;
        this.age = age;
        this.isNumberConfirmed = isNumberConfirmed;
        this.isReadyToRelocate = isReadyToRelocate;
    }


    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public boolean isNumberConfirmed() {
        return isNumberConfirmed;
    }

    public boolean isReadyToRelocate() {
        return isReadyToRelocate;
    }
}
