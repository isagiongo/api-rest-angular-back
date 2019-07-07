package com.isagiongo.apirestangular.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.bytebuddy.matcher.CollectionOneToOneMatcher;
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
                    .body("email", Is.is("isagiongo@hotmail.com"));
    }

    @Test
    public void deveRetornarBadRequestSeNomeNaoForInformado() {
        String json = "{\"name\":\"\",\"email\": \"isagiongo@hotmail.com\"}";
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(json)
                    .post("/users")
                .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void deveRetornarBadRequestSeEmailNaoForInformado() {
        String json = "{\"name\":\"Isadora\",\"email\": \"\"}";
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/users")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void deveRetornarBadRequestSeEmailNaoForValido() {
        String json = "{\"name\":\"Isadora\",\"email\": \"emailinvalido\"}";
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/users")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void deveRetornarUserSeIdExistir() {
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

    @Test
    public void deveRetornarNotFoundSeIdNaoExistir() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .get("/users/9")
                .then()
                    .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarNotFoundSeIdNaoExistirAoTentarDeletar() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .delete("/users/9")
                .then()
                    .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarOKAoDeletarUserExistente() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .delete("/users/3")
                .then()
                    .statusCode(HttpStatus.OK.value());
    }
}
