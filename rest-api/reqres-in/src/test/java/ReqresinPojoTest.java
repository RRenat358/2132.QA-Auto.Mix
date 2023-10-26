
import io.restassured.http.ContentType;
import models.ColorsDto;
import models.UserFieldTime;
import models.UserFieldTimeResponse;
import models.auth.UnSuccessUserUserAuth;
import models.auth.UserAuth;
import models.auth.SuccessUserAuth;
import models.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restSpecification.RestSpec;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresinPojoTest {

    private static final String BASE_PATH = "https://reqres.in";


    @Test
    @DisplayName("Аватары содержат id пользователей \n" +
                 "email имеет окончание reqres.in")
    public void compareIdAndNameAvatar() {
        List<UserDto> userDtoList = given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_PATH + "/api/users?page=2")
                .then()
                .log().all()
                .extract().body().jsonPath().getList("data", UserDto.class);

        //проверка аватар содержит айди
        userDtoList.forEach(userDto ->
                Assertions.assertTrue(userDto.getAvatar().contains(userDto.getId().toString())));

        //проверка почты оканчиваются на reqres.in
        Assertions.assertTrue(userDtoList.stream().allMatch(userDto ->
                userDto.getEmail().endsWith("@reqres.in")));


        //2 способ сравнивать значения через получения списков
        //список с аватарками
        List<String>userAvatarsList  = userDtoList.stream()
                .map(UserDto::getAvatar)
                .collect(Collectors.toList());
        //список с айди
        List<String> userIdList = userDtoList.stream()
                .map(x->x.getId().toString())
                .collect(Collectors.toList());
        //проверка через сравнение двух списков
        for (int i = 0; i<userAvatarsList.size(); i++){
            Assertions.assertTrue(userAvatarsList.get(i).contains(userIdList.get(i)));
        }



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
    @DisplayName("Не успешная регистрация (не введен пароль)")
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

    @Test
    @DisplayName("Года отсортированы по возрастанию")
    public void checkSortedYearsTest(){
        RestSpec.installSpec(RestSpec.requestSpec(BASE_PATH),
                RestSpec.responseSpecStatusCode(200));
        List<ColorsDto> data = given()
                .when()
                .get("/api/unknown")
                .then()
                .log().all()
                .extract().body().jsonPath().getList("data", ColorsDto.class);

        List<Integer> dataYears = data.stream().map(ColorsDto::getYear).collect(Collectors.toList());
        List<Integer> sortedDataYears = dataYears.stream().sorted().collect(Collectors.toList());
        Assertions.assertEquals(dataYears, sortedDataYears);
        System.out.println(dataYears);
        System.out.println(sortedDataYears);
    }

    @Test
    @DisplayName("Удаление 2-го пользователя")
    public void deleteUserTest(){
        RestSpec.installSpec(RestSpec.requestSpec(BASE_PATH),
                RestSpec.responseSpecStatusCode(204));
        given().when().delete("/api/users/2")
                .then()
                .log().all();
    }


    //flaky test!!!
    @Test
    @DisplayName("обновить информацию о пользователе и \n" +
                 "сравнить дату обновления с текущей датой на машине")

    public void checkServerAndPcDateTest(){
        RestSpec.installSpec(RestSpec.requestSpec(BASE_PATH),
                RestSpec.responseSpecStatusCode(200));
        UserFieldTime userFieldTime = new UserFieldTime("morpheus","zion resident");
        UserFieldTimeResponse response = given()
                .body(userFieldTime)
                .when()
                .put("/api/users/2")
                .then().log().all()
                .extract().as(UserFieldTimeResponse.class);
        String currentTime = Clock.systemUTC().instant().toString();

        //так как время считается в плоть до миллисекунд,
        // необходимо убрать последние N символов,
        // чтобы время было примерно одинаковое
        String regex5 = "(.{5})$";
        String responseShort = response.getUpdatedAt().replaceAll(regex5,"");

        String regex11 = "(.{11})$";
        String currentTimeShort = currentTime.replaceAll(regex11,"");

        Assertions.assertEquals(responseShort,currentTimeShort);
    }



}
