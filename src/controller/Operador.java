package controller;

import enums.Sexo;
import io.EscritorDeArquivo;
import io.LeitorDeArquivo;
import model.Endereco;
import model.Pet;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Operador {
    String path = "formulario.txt";
    Endereco endereco = new Endereco();
    Scanner sc = new Scanner(System.in);
    EscritorDeArquivo escritorDeArquivo = new EscritorDeArquivo();


    public void cadastrar() throws FileNotFoundException {
        LeitorDeArquivo leitorDeArquivo = new LeitorDeArquivo();
        leitorDeArquivo.ler(path);
        Pet pet = new Pet();

        System.out.print("1: ");
        pet.setNome(sc.next());

        System.out.print("2: ");
        pet.setSobrenome(sc.next());

        System.out.print("3: ");
        String sexo = sc.next();

        if (sexo.equalsIgnoreCase("macho")) {
            pet.setSexo(Sexo.MACHO);
        } else if (sexo.equalsIgnoreCase("fêmea")) {
            pet.setSexo(Sexo.FEMEA);
        } else {
            System.out.println("Sexo indefinido");
        }

        System.out.println("4: ");
        System.out.print("Rua: ");
        endereco.setRua(sc.next());
        String rua = endereco.getRua();
        System.out.print("Número: ");
        endereco.setNumero(sc.next());
        String numero = endereco.getRua();
        System.out.print("Cidade: ");
        endereco.setCidade(sc.next());
        String cidade = endereco.getCidade();
        pet.setEndereco(rua, numero, cidade);
        System.out.print("5: ");
        pet.setIdade(sc.nextDouble());

        System.out.print("6: ");
        pet.setPeso(sc.nextDouble());

        System.out.print("7: ");
        pet.setRaca(sc.next());

        escritorDeArquivo.escrever(pet);
    }
}
