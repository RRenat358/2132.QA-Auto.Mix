package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColorsDto {

    private Long id;
    private String name;
    private Integer year;
    private String color;
    private String pantone_value;


}
