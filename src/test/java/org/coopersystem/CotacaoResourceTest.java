package org.coopersystem;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

@QuarkusTest
public class CotacaoResourceTest {

    @Test
    public void testCotacoesEndpoint() {
        given()
                .when().get("/cotacoes/06-21-2022")
                .then()
                .statusCode(200);
    }

}
