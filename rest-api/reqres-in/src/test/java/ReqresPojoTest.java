
import io.restassured.http.ContentType;
import models.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restSpecification.RestSpec;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ReqresPojoTest {

    private static final String BASE_PATH = "https://reqres.in";


    @Test
    @DisplayName("Аватары содержат id пользователей")
    public void compareIdAndNameAvatar() {
        List<UserDto> userDtoList = given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_PATH + "/api/users?page=2")
                .then()
                .log().all()
                .extract().body().jsonPath().getList("data", UserDto.class);

        userDtoList.forEach(userDto ->
                Assertions.assertTrue(userDto.getAvatar().contains(userDto.getId().toString())));

        Assertions.assertTrue(userDtoList.stream().allMatch(userDto ->
                userDto.getEmail().endsWith("@reqres.in")));

    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successUserRegTest() {
        Integer UserId = 4;
        String UserPassword = "QpwL5tke4Pnpja7X4";
        RestSpec.installSpec(RestSpec.requestSpec(BASE_PATH), RestSpec.responseSpecStatusCode(200));



    }




}
