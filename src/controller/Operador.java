package controller;

import enums.Sexo;
import enums.Tipo;
import io.Escritor;
import io.LeitorDeArquivo;
import model.Endereco;
import model.Pet;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Operador {
    String path = "formulario.txt";
    Scanner sc = new Scanner(System.in);
    Escritor escritorDeArquivo = new Escritor();
    LeitorDeArquivo leitorDeArquivo = new LeitorDeArquivo();


    public void cadastrar() throws FileNotFoundException {
        Endereco endereco = new Endereco();
        Pet pet = new Pet();

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

    public List<String> todosPetsParaLista() {
        var pathsOfPets = leitorDeArquivo.listOfPaths();
        List<String> listOfPets = new ArrayList<>();

        for (String pathsOfPet : pathsOfPets) {
            List<String> petRaw = leitorDeArquivo.fileToString(pathsOfPet);
            String petFormated = leitorDeArquivo.listOfPetsOutput(petRaw);
            listOfPets.add(petFormated);
        }
        return listOfPets;
    }

    public void listarTodosPets(List<String> lista) {

        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i));
        }
    }

    public List<String> buscarPetsPorNomeOuSobrenome(String nome) {
        List<String> pets = todosPetsParaLista();
        return pets.stream().filter(p -> p.contains(nome.toLowerCase(Locale.ROOT))).toList();
    }

    public List<String> buscaPorSexo(String sexo) {
        List<String> pets = todosPetsParaLista();
        return pets.stream().filter(p -> p.contains(sexo.toLowerCase(Locale.ROOT))).toList();
    }

    public List<String> buscaPorIdade(double idade) {
        var pets = todosPetsParaLista();
        List<String> petsPorIdade = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("5 - " + idade);
        for (String pet : pets) {
            if (pet.contains(sb.toString())) {
                petsPorIdade.add(pet);
            }
        }

        return petsPorIdade;
    }


}
