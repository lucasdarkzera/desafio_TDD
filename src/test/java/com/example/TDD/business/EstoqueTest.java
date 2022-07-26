package com.example.TDD.business;

import com.example.TDD.model.Ingrediente;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class EstoqueTest {

    private Estoque estoque;

    private final String EXPECTED_TRHOWN_NOT_FOUND = "Excecao nao encontrada";

    @BeforeEach
    void initialize(){
        this.estoque = defaultData(new Estoque());
    }

    public Estoque defaultData(Estoque estoque){
        Ingrediente n = new Ingrediente();
        n.setNome("Arroz tipo 1");
        n.setPeso(15f);
        estoque.getEstoque().put(n, 10);

        Ingrediente n1 = new Ingrediente();
        n1.setNome("Nutella");
        n1.setPeso(200f);
        estoque.getEstoque().put(n1, 3);
        return estoque;
    }

    @Test
    public void cadastrarIngredienteValido(){
        Ingrediente ingrediente = new Ingrediente("Mouse Zowie BKB 33342", 200f);
        var rst = estoque.cadastrarIngrediente(ingrediente);
        assertTrue(rst);
    }

    @Test
    public void cadastrarIngredienteJaExistente(){
        Ingrediente ingrediente = new Ingrediente("Nutella", 200f);

        Exception excp = assertThrows(
                IllegalArgumentException.class,
                () -> estoque.cadastrarIngrediente(ingrediente),
                "Expected . "
        );

        assertEquals(excp.getMessage(), "Ingrediente já cadastrado");
    }

    @Test
    @Disabled
    public void cadastrarIngredienteInvalido(){
        Ingrediente ingrediente = new Ingrediente(null, 200f);

        Exception excp = assertThrows(
                IllegalArgumentException.class,
                () -> estoque.cadastrarIngrediente(ingrediente),
                EXPECTED_TRHOWN_NOT_FOUND
        );

        assertEquals(excp.getMessage(), "Ingrediente inválido");

    }

    @Test
    public void descadastrarIngredienteInvalido(){
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome("Nescau");
        ingrediente.setPeso(23.3F);

        Exception exc = assertThrows(
                IllegalArgumentException.class,
                () -> estoque.descadastrarIngrediente(ingrediente),
                EXPECTED_TRHOWN_NOT_FOUND
        );

        assertTrue(exc.getMessage().equals("Ingrediente nao cadastrado"));

    }

    @Test
    public void descadastrarIngredienteExistente(){
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome("Arroz tipo 1");
        ingrediente.setPeso(15f);

        assertTrue(estoque.descadastrarIngrediente(ingrediente));

    }

    @Test
    @DisplayName("Adiciona quantidade de Ingrediente nao cadastrado")
    public void adicionarQuantidadeDoIngredienteEmEstoque_IngredienteNaoExistente() {
        final int qtd = 3;
        Ingrediente ingrediente  = new Ingrediente();
        ingrediente.setNome("Café 3 coracoes tradicional");
        ingrediente.setPeso(250f);

        Exception exc = assertThrows(
                IllegalArgumentException.class,
                () -> estoque.adicionarQuantidadeDoIngredienteEmEstoque(ingrediente, 3),
                "Expected "
        );

        assertTrue(exc.getMessage().equals("Ingrediente nao cadastrado"));
    }

    @Test
    @DisplayName("Adiciona invalida quantidade de Ingrediente")
    public void adicionarQuantidadeDoIngredienteEmEstoque_QuantidadeInvalida() {
        Ingrediente ingrediente  = new Ingrediente();
        ingrediente.setNome("Arroz tipo 1");
        ingrediente.setPeso(15f);

        Exception exc = assertThrows(
                IllegalArgumentException.class,
                () -> estoque.adicionarQuantidadeDoIngredienteEmEstoque(ingrediente, -3),
                "Expected "
        );

        assertTrue(exc.getMessage().equals("Quantidade inválida"));
    }

    @Test
    public void adicionarQuantidadeDoIngredienteEmEstoque_ComSucesso() {
        Ingrediente ingrediente = new Ingrediente("Arroz tipo 1", 15f);
        var result= estoque.adicionarQuantidadeDoIngredienteEmEstoque(ingrediente, 3);
        assertTrue(result);
    }

    @Test
    public void reduzirQuantidadeDoIngredienteEmEstoqueIngredienteNaoExistente(){
        final int qtd = 3;
        Ingrediente ingrediente  = new Ingrediente();
        ingrediente.setNome("Café 3 coracoes tradicional");
        ingrediente.setPeso(250f);

        Exception exc = assertThrows(
                IllegalArgumentException.class,
                () -> estoque.reduzirQuantidadeDoIngredienteEmEstoque(ingrediente, 3),
                EXPECTED_TRHOWN_NOT_FOUND
        );

        assertTrue(exc.getMessage().equals("Ingrediente nao cadastrado"));
    }


    @Test
    public void reduzirQuantidadeDoIngredienteEmEstoqueQuantidadeInvalida(){
        final int qtd = 3;
        Ingrediente ingrediente  = new Ingrediente();
        ingrediente.setNome("Café 3 coracoes tradicional");
        ingrediente.setPeso(250f);

        Exception exc = assertThrows(
                IllegalArgumentException.class,
                () -> estoque.reduzirQuantidadeDoIngredienteEmEstoque(ingrediente, -3),
                EXPECTED_TRHOWN_NOT_FOUND
        );

        assertTrue(exc.getMessage().equals("Quantidade inválida"));
    }

    @Test
    public void reduzirQuantidadeDoIngredienteEmEstoqueComSucesso(){
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome("Arroz tipo 1");
        ingrediente.setPeso(15f);

        Estoque actualEstoque = estoque.reduzirQuantidadeDoIngredienteEmEstoque(ingrediente, 3);
        assertEquals(actualEstoque.getEstoque().get(ingrediente), 7);
    }

}
