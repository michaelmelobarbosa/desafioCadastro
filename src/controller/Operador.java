package controller;

import enums.Sexo;
import enums.Tipo;
import io.EscritorDeArquivo;
import io.LeitorDeArquivo;
import model.Endereco;
import model.Pet;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Operador {
    String path = "formulario.txt";
    Scanner sc = new Scanner(System.in);
    EscritorDeArquivo escritorDeArquivo = new EscritorDeArquivo();


    public void cadastrar() throws FileNotFoundException {
        Endereco endereco = new Endereco();
        Pet pet = new Pet();

        LeitorDeArquivo leitorDeArquivo = new LeitorDeArquivo();
        leitorDeArquivo.ler(path);

        System.out.print("1: ");
        pet.setNome(sc.next());
        pet.setSobrenome(sc.next());

        System.out.print("2: ");
        String tipo = sc.next();
        if (tipo.equalsIgnoreCase("cachorro")) {
            pet.setTipo(Tipo.CACHORRO);
        } else if (tipo.equalsIgnoreCase("gato")) {
            pet.setTipo(Tipo.GATO);
        } else {
            System.out.println("Tipo indefinido");
        }

        System.out.print("3: ");
        String sexo = sc.next();

        if (sexo.equalsIgnoreCase("macho")) {
            pet.setSexo(Sexo.MACHO);
        } else if (sexo.equalsIgnoreCase("femea") ||
                sexo.equalsIgnoreCase("fêmea")) {
            pet.setSexo(Sexo.FEMEA);
        } else {
            System.out.println("Sexo indefinido");
        }

        System.out.println("4: ");
        System.out.print("Rua: ");
        endereco.setRua(sc.next());
        System.out.print("Número: ");
        endereco.setNumero(sc.next());
        System.out.print("Cidade: ");
        endereco.setCidade(sc.next());
        pet.setEndereco(endereco);

        System.out.print("5: ");
        pet.setIdade(sc.nextDouble());

        System.out.print("6: ");
        pet.setPeso(sc.nextDouble());

        System.out.print("7: ");
        pet.setRaca(sc.next());

        String path = escritorDeArquivo.outputFormatter(pet);

        escritorDeArquivo.escrever(pet, path);
    }
}
