import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Класс для описания атрибутов кандидата из страницы с резюме на hh
 */

@Getter
@AllArgsConstructor
public class ResumePage2 {
    /**
     * Атрибуты для кандидата
     */
    private final String gender;
    private final String city;
    private final int age;
    private final boolean isNumberConfirmed;
    private final boolean isReadyToRelocate;


}
