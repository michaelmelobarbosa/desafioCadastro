package model;

import enums.Sexo;
import enums.Tipo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.SequencedCollection;

public class Pet {
    private String nome;
    private String sobrenome;
    private Tipo tipo;
    private Sexo sexo;
    private Endereco endereco;
    private Double idade;
    private Double peso;
    private String raca;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public List<String> getEndereco() {
        return List.of(endereco.getRua(), endereco.getNumero(), endereco.getCidade());
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Double getIdade() {
        return idade;
    }

    public void setIdade(Double idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    @Override
    public boolean equals(Object o) {        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(nome, pet.nome) && Objects.equals(idade, pet.idade) && Objects.equals(raca, pet.raca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, idade, raca);
    }

    public void setEndereco(String rua, String numero, String cidade) {

        this.endereco = new Endereco(rua, numero, cidade);
    }
}
