package br.com.Salazar.rest.tests.refatoracao;

import br.com.Salazar.rest.core.BaseTest;
import io.restassured.RestAssured;
import org.junit.Test;

import static br.com.Salazar.rest.utils.BarrigaUtils.getIdContaPeloNome;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ContasTest extends BaseTest {

    @Test
    public void deveIncluirContaComSucesso() {
         given()
                .body("{ \"nome\": \"Conta inserida\" }")
                .when()
                .post("/contas")
                .then()
                .statusCode(201)
                .extract().path("id");
    }

    @Test
    public void deveAlterarContaComSucesso() {
        Integer CONTA_ID = getIdContaPeloNome("Conta para alterar");
        given()
                .body("{ \"nome\": \"Conta alterada\" }")
                .pathParam("id", CONTA_ID)
                .when()
                .put("/contas/{id}")
                .then()
                .statusCode(200)
                .body("nome", is("Conta alterada"));
    }

    @Test
    public void naoDeveInserirContaMesmoNome() {
        given()
                .body("{ \"nome\": \"Conta mesmo nome\" }")
                .when()
                .post("/contas")
                .then()
                .log().all()
                .statusCode(400)
                .body("error", is("Já existe uma conta com esse nome!"));
    }
}
