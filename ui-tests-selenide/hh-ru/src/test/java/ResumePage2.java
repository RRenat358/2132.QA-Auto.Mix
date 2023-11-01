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
    private final int age;
    private final String city;
    private final boolean isReadyToRelocate;
    private final boolean isNumberConfirmed;


}
