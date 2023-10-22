package restSpecification;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestSpec {

    public static RequestSpecification requestSpec(String baseUrl) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType("application/json")
                .build();
    }

    public static ResponseSpecification responseSpecStatusCode(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }

    public static void installSpec(RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    public static void installSpec(RequestSpecification requestSpec) {
        RestAssured.requestSpecification = requestSpec;
    }

    public static void installSpec(ResponseSpecification responseSpec) {
        RestAssured.responseSpecification = responseSpec;
    }


}
