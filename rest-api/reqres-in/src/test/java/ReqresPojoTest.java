
import io.restassured.http.ContentType;
import models.auth.UnSuccessUserUserAuth;
import models.auth.UserAuth;
import models.auth.SuccessUserAuth;
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
        Long userId = 4L;
        String userPassword = "QpwL5tke4Pnpja7X4";
        RestSpec.installSpec(RestSpec.requestSpec(BASE_PATH),
                RestSpec.responseSpecStatusCode(200));
        UserAuth userAuth = new UserAuth("eve.holt@reqres.in","pistol");

        SuccessUserAuth successUserAuth = given()
                .body(userAuth)
                .when()
                .post("/api/register")
                .then()
                .log().all()
                .extract().as(SuccessUserAuth.class);

        Assertions.assertNotNull(successUserAuth.getId());
        Assertions.assertNotNull(successUserAuth.getToken());
        Assertions.assertEquals(userId, successUserAuth.getId());
        Assertions.assertEquals(userPassword, successUserAuth.getToken());

    }

    @Test
    @DisplayName("Не успешная регистрация")
    public void unSuccessUserRegTest(){
        RestSpec.installSpec(RestSpec.requestSpec(BASE_PATH),
                RestSpec.responseSpecStatusCode(400));
        UserAuth userAuth = new UserAuth("sydney@fife","");

        UnSuccessUserUserAuth unSuccessUserUserAuth = given()
                .body(userAuth)
                .when()
                .post("/api/register")
                .then()  //.assertThat().statusCode(400) проверить статус ошибки, если не указана спецификация
                .log().body()
                .extract().as(UnSuccessUserUserAuth.class);

        Assertions.assertNotNull(unSuccessUserUserAuth.getError());
        Assertions.assertEquals("Missing password", unSuccessUserUserAuth.getError());
    }







}
