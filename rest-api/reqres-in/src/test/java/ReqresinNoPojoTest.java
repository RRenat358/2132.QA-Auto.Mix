import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restSpecification.RestSpec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ReqresinNoPojoTest {

    private static final String BASE_PATH = "https://reqres.in";


    @Test
    public void getUserData_checkIdAndAvatar_checkTailEmail() {
        RestSpec.installSpec(RestSpec.requestSpec(BASE_PATH),
                RestSpec.responseSpecStatusCode(200));

        Response response = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .body("page", equalTo(2))
                .body("data.id", notNullValue())
                .body("data.email", notNullValue())
                .body("data.first_name", notNullValue())
                .body("data.last_name", notNullValue())
                .body("data.avatar", notNullValue())
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> emailList = jsonPath.get("data.email");
        List<Integer> idList = jsonPath.get("data.id");
        List<String> avatarList = jsonPath.get("data.avatar");

        for(int i = 0; i<avatarList.size(); i++){
            Assertions.assertTrue(avatarList.get(i).contains(idList.get(i).toString()));
        }

        Assertions.assertTrue(emailList.stream().allMatch(x->x.endsWith("@reqres.in")));

    }


    @Test
    @DisplayName("Успешная регистрация")
    public void successUserAuth() {
        RestSpec.installSpec(RestSpec.requestSpec(BASE_PATH),
                RestSpec.responseSpecStatusCode(200));

        Map<String, String> userLogoPass = new HashMap<>();
        userLogoPass.put("email", "eve.holt@reqres.in");
        userLogoPass.put("password", "pistol");

        Response response = given()
                .body(userLogoPass)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Integer id = jsonPath.get("id");
        String token = jsonPath.get("token");

        Assertions.assertEquals(4,id);
        Assertions.assertEquals("QpwL5tke4Pnpja7X4", token);

    }

    @Test
    @DisplayName("Не успешная регистрация (не введен пароль)")
    public void unSuccessUserAuth(){
        RestSpec.installSpec(RestSpec.requestSpec(BASE_PATH),
                RestSpec.responseSpecStatusCode(400));
        Map<String, String> userLogoPass = new HashMap<>();
        userLogoPass.put("email", "sydney@fife");
        Response response = given()
                .body(userLogoPass)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assertions.assertEquals("Missing password", error);

    }



}
