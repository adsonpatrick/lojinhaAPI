package modulos.produto;

import dataFactory.produtoDataFactory;
import dataFactory.usuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Testes de API Rest do modulo de Produto")
public class ProdutoTest {
    private String token;

    @BeforeEach
    public void beforeEach(){
        // Configurando os dados da API Rest da Lojinha
        baseURI = "http://165.227.93.41";
        basePath = "/lojinha";

        // Obter o token do usuário
        this.token = given()
                .contentType(ContentType.JSON)
                .body(usuarioDataFactory.criarUsuarioAdministrador())
            .when()
                .post("/v2/login")
            .then()
                .extract()
                    .path("data.token");
    }

    @Test
    @DisplayName("Validar que o valor do produto igual a 0.00 não é permitido")
    public void testValidarLimiteZeradoProibidoValorProduto() {

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(produtoDataFactory.cirarProdutoComumComOValorIgualA(0.00))
            .when()
                .post("/v2/produtos")
            .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);

    }

    @Test
    @DisplayName("Validar que o valor do produto igual a 7000.01 não é permitido")
    public void testValidarLimitesMaiorSeteMilProibidoValorProduto() {

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(produtoDataFactory.cirarProdutoComumComOValorIgualA(7000.01))
            .when()
                .post("/v2/produtos")
            .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);

    }

}
