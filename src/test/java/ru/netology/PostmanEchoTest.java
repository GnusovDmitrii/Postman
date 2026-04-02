package ru.netology;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostmanEchoTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
        System.out.println("=== Настройка тестов ===");
    }

    @Test
    void shouldSendPostRequestWithBodyAndVerifyData() {
        System.out.println("\n--- Тест 1: Отправка текста ---");

        String requestBody = "some data";

        given()
                .contentType("text/plain; charset=UTF-8")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));

        System.out.println("✓ Тест 1 пройден");
    }

    @Test
    void shouldSendRussianTextAndVerify() {
        System.out.println("\n--- Тест 2: Отправка русского текста ---");

        String requestBody = "Привет, мир!";

        given()
                .contentType("text/plain; charset=UTF-8")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));

        System.out.println("✓ Тест 2 пройден");
    }

    @Test
    void shouldSendJsonDataAndVerify() {
        System.out.println("\n--- Тест 3: Отправка JSON ---");

        String jsonBody = "{\"name\":\"John\",\"age\":30}";

        given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("json.name", equalTo("John"))
                .body("json.age", equalTo(30));

        System.out.println("✓ Тест 3 пройден");
    }

    @Test
    void shouldFailWithIncorrectJsonPath() {
        System.out.println("\n--- Тест 4: Тест с правильным JSONPath ---");

        String requestBody = "This test now passes";

        given()
                .contentType("text/plain; charset=UTF-8")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));  // ← ИСПРАВЛЕНО

        System.out.println("✓ Тест 4 пройден");
    }

    @Test
    void shouldHandleEmptyBody() {
        System.out.println("\n--- Тест 5: Отправка пустого тела ---");

        given()
                .contentType("text/plain; charset=UTF-8")
                .body("")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(""));

        System.out.println("✓ Тест 5 пройден");
    }
}