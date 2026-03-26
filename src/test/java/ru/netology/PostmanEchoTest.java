package ru.netology;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class PostmanEchoTest {

    @Test
    void shouldSendPostRequestAndVerifyData() {
        String testData = "Hello, Postman Echo!";

        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8")
                .body(testData)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(testData));
    }

    @Test
    void shouldSendPostRequestWithRussianData() {
        String testData = "Привет, Postman Echo!";

        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8")
                .body(testData)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(testData));
    }

    @Test
    void shouldFailTest() {
        String testData = "Test data";

        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8")
                .body(testData)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("wrong.field", equalTo(testData));
    }
}