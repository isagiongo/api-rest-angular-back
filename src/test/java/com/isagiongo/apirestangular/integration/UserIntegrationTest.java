package com.isagiongo.apirestangular.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {


    @LocalServerPort
    private int randomPort;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost/";
        RestAssured.port = randomPort;
    }

    @Test
    public void deveRetornarOKaoBuscarUsers() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .get("/users")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarUserSalvo() {
        String json = "{\"name\":\"isadora\",\"email\": \"isagiongo@hotmail.com\"}";
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(json)
                    .post("/users")
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("id", Is.is(6))
                    .body("name", Is.is("isadora"))
                    .body("email", Is.is("isagiongo@hotmail.com"))
        ;
    }

    @Test
    public void deveRetornarUser() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .get("/users/1")
                .then()
                    .body("id", Is.is(1))
                    .body("name", Is.is("John"))
                    .body("email", Is.is("john@domain.com"))
                    .statusCode(HttpStatus.OK.value());
    }
}
