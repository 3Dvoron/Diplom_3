package model;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import setup.Setup;

public class User extends Setup {
    @Step("удаляет пользователя")
    public ValidatableResponse delete(String token) {
        return RestAssured.given()
                .header("authorization", token)
                .when()
                .delete(Setup.DELETE_USER_URI)
                .then();
    }
}
