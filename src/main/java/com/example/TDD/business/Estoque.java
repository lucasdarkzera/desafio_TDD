package com.example.TDD.business;

import com.example.TDD.model.Armazem;
import com.example.TDD.model.Ingrediente;

import javax.swing.tree.TreePath;
import java.util.Collection;
import java.util.Objects;
import java.util.TreeMap;

public class Estoque {

    private static Armazem armazem = new Armazem();

    private TreeMap<Ingrediente, Integer> estoque =  new TreeMap<>();

    public Estoque(){
        estoque = new TreeMap<>();
    }

    public boolean descadastrarIngrediente(Ingrediente ingrediente) {
        if (!estoque.containsKey(ingrediente)){
            throw new IllegalArgumentException("Ingrediente nao cadastrado");
        }
        estoque.remove(ingrediente);
        return true;
    }

    public TreeMap<Ingrediente, Integer> getEstoque(){
        return this.estoque;
    }

    /*
        Adiciona uma determinada quantidade de um ingrediente específico no estoque.
        Retornando IllegaLArgumentException com a seguinte mensagem de erro:
            caso o ingrediente não exista no estoque ou a quantidade informada seja menor ou igual a zero.
                -> Throws :: _Ingrediente não encontrado ou quantidade inválida_
    */
    public boolean adicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {

        if (quantidade <= 0){
            throw new IllegalArgumentException("Quantidade inválida");
        }

        if (!estoque.containsKey(ingrediente)){
            throw new IllegalArgumentException("Ingrediente nao cadastrado");
        }

        final Integer quantidadeAtual = estoque.get(ingrediente);
        estoque.replace(ingrediente, quantidadeAtual + quantidade);
        return true;
    }

    public boolean cadastrarIngrediente(Ingrediente ingrediente) {

//        if (Objects.isNull(ingrediente)) {
//            throw new IllegalArgumentException("Ingrediente inválido");
//        }

        if (this.getEstoque().containsKey(ingrediente)){
            throw new IllegalArgumentException("Ingrediente já cadastrado");
        }

        this.getEstoque().put(ingrediente, 0);
        return true;
    }

    public Estoque reduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, int quantidade) {

        if (!isQuantidadeValid(quantidade)){
            throw new IllegalArgumentException("Quantidade inválida");
        }

        if (!estoque.containsKey(ingrediente)){
            throw new IllegalArgumentException("Ingrediente nao cadastrado");
        }

        var quantidadeAtualDoItem = estoque.get(ingrediente);
        estoque.replace(ingrediente, quantidadeAtualDoItem - quantidade);
        return this;
    }

    boolean containsIngrediente(Ingrediente ingrediente){
        if (!estoque.containsKey(ingrediente)){
            throw new IllegalArgumentException("Ingrediente nao cadastrado");
        }
        return true;
    }


    boolean isIngredienteValid(Ingrediente ingrediente){
        if (!Objects.nonNull(ingrediente)) {
            throw new IllegalArgumentException("Ingrediente inválido");
        }
        return true;
    }

    boolean isQuantidadeValid(int quantidade){
        if (!(quantidade > 0)) {
            throw new IllegalArgumentException("Quantidade inválida");
        }
        return true;
    }
}