package model;

import java.util.Objects;

public class Endereco {
    private String numero;
    private String cidade;
    private String rua;

    public Endereco(String rua, String numero, String cidade) {
       this.rua = rua;
       this.numero = numero;
       this.cidade = cidade;
    }

    public Endereco(){}

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    @Override
    public String toString() {
        return rua + ", " + numero + ", " + cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(numero, endereco.numero) && Objects.equals(cidade, endereco.cidade) && Objects.equals(rua, endereco.rua);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, cidade, rua);
    }
}


