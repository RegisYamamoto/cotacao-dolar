package org.coopersystem;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.NotFoundException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class CotacaoResourceTest {

    @Test
    @DisplayName("deve testar endpoint de consulta de cotação com sucesso")
    public void getCotacoesTest() {
        var response =
                given()
                    .contentType(ContentType.JSON)
                .when()
                    .get("/cotacoes/06-21-2022")
                .then()
                    .extract().response();

        assertEquals(200, response.statusCode());
    }

    @Test
    @DisplayName("deve dar erro 404 quando o formato de data for inválida")
    public void getCotacoesExceptionTest() throws NotFoundException {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/cotacoes/06-21-2")
        .then()
            .statusCode(404);
    }

}
