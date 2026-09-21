package model;

public class Endereco {
    private String numero;
    private String cidade;
    private String rua;

    public Endereco(String rua, String numero, String cidade) {

    }


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
}


