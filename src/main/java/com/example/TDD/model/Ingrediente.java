package com.example.TDD.model;

import java.util.Objects;

public class Ingrediente implements Comparable<Ingrediente> {

    public Ingrediente(String nome, float peso){
        this.nome = nome;
        this.peso = peso;
    }

    public Ingrediente(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingrediente that = (Ingrediente) o;
        return Float.compare(that.peso, peso) == 0 && nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, peso);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    private String nome;
    private float peso;

    @Override
    public int compareTo(Ingrediente ingrediente) {
       return this.nome.length() - ingrediente.getNome().length();
    }
}
