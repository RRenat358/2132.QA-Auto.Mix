package models.users;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    public String street;
    public String suite;
    public String city;
    public String zipcode;
    public Geo geo;


}
