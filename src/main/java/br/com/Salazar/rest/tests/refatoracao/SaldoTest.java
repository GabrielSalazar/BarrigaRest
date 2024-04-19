package br.com.Salazar.rest.tests.refatoracao;

import br.com.Salazar.rest.core.BaseTest;
import org.junit.Test;

import static br.com.Salazar.rest.utils.BarrigaUtils.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SaldoTest extends BaseTest {
    @Test
    public void deveCalcularSaldoContas() {
        Integer CONTA_ID = getIdContaPeloNome("Conta para saldo");
        given()
                .when()
                .get("/saldo")
                .then()
                .statusCode(200)
                .body("find{it.conta_id == "+CONTA_ID+"}.saldo", is("534.00"));
    }

}
