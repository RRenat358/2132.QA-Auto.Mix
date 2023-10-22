
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ReqresPojoTest {

    private static final String BASE_PATH = "https://reqres.in";


    @Test
    public void compareIdAndNameAvatar() {
        List<UserDTO> userDTOList = given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_PATH + "/api/users?page=2")
                .then()
                .log().all()
                .extract().body().jsonPath().getList("data", UserDTO.class);

        userDTOList.forEach(userDTO ->
                Assertions.assertTrue(userDTO.getAvatar().contains(userDTO.getId().toString())));

        Assertions.assertTrue(userDTOList.stream().allMatch(userDTO ->
                userDTO.getEmail().endsWith("@reqres.in")));

    }


}
