package model;

import enums.Sexo;
import enums.Tipo;

import java.util.Objects;

public class PetDto {
    private String nome;
    private Tipo tipo;
    private Sexo sexo;
    private Double idade;
    private Double peso;
    private String raca;
    private String endereco;

    public PetDto(String nome, Tipo tipo, Sexo sexo, Double idade, Double peso, String raca, String endereco) {
        this.nome = nome;
        this.tipo = tipo;
        this.sexo = sexo;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
        this.endereco = endereco;
    }

    public PetDto(String nome, Sexo sexo, Double idade, Double peso, String raca, String endereco) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
        this.endereco = endereco;
    }

    public PetDto(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PetDto petDto = (PetDto) o;
        return Objects.equals(nome, petDto.nome) && tipo == petDto.tipo && sexo == petDto.sexo && Objects.equals(idade, petDto.idade) && Objects.equals(peso, petDto.peso) && Objects.equals(raca, petDto.raca) && Objects.equals(endereco, petDto.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, tipo, sexo, idade, peso, raca, endereco);
    }
}
