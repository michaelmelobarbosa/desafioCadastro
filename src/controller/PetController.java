package controller;

import enums.Sexo;
import enums.Tipo;
import exceptions.ValidationException;
import model.Endereco;
import model.Pet;
import model.PetDto;
import service.PetService;
import util.LeitorDeArquivo;
import util.TextNormalizer;

import java.sql.SQLOutput;
import java.util.*;

public class PetController {
    private final Scanner sc = new Scanner(System.in);
    private final PetService service;
    private final PetDto petDto;

    public PetController(PetService service, PetDto petDto) {
        this.service = service;
        this.petDto = petDto;
    }

    public void cadastrar() {
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

        service.cadastrar(pet);
        System.out.println(pet.getNome() + " " + pet.getSobrenome() + " cadastrado(a) com sucesso!");

    }

    public List<Pet> listaDePets() {
        return service.listaDePets();
    }

    public void printList(List<Pet> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    public List<Pet> buscaPet() {
        String nomeESobrenome = null;
        String sexoEntrada = null;
        Double idade = null;
        Double peso = null;
        String raca = null;
        String enderecoEntrada = null;

        Tipo tipo = escolhaTipo();

        mostrarMenuBusca();

        String escolhas = sc.nextLine().replaceAll("\\s+", "");

        for (int i = 0; i < escolhas.length(); i++) {
            switch (escolhas.charAt(i)) {
                case '1' -> nomeESobrenome = sc.nextLine().trim();
                case '2' -> sexoEntrada = sc.nextLine().trim();
                case '3' -> idade = Double.parseDouble(sc.nextLine().trim());
                case '4' -> peso = Double.parseDouble(sc.nextLine().trim());
                case '5' -> raca = sc.nextLine().trim();
                case '6' -> enderecoEntrada = sc.nextLine().trim();
                default -> mostrarMenuBusca();
            }
        }

        Sexo sexo = sexoEntrada == null ? null : parseSexo(sexoEntrada);
        String endereco = (enderecoEntrada == null || enderecoEntrada.isBlank()) ? null : enderecoEntrada;

        PetDto pet = new PetDto(nomeESobrenome, tipo, sexo, idade, peso, raca, endereco);

        return service.buscaPet(pet);
    }

    private static void mostrarMenuBusca() {
        System.out.println("Escolha de 1 a 2 critérios que deseja buscar: ('Tipo' é um critério obrigatório)");
        System.out.println("""
                1. Nome ou sobrenome
                2. Sexo
                3. Idade
                4. Peso
                5. Raça
                6. Endereço
                """);
    }

    private Sexo parseSexo(String valor) {
        return switch (TextNormalizer.normalize(valor)) {
            case "macho" -> Sexo.MACHO;
            case "femea" -> Sexo.FEMEA;
            default -> throw new ValidationException("Sexo", "Sexo Inválido");
        };
    }

    private Tipo escolhaTipo() {
        while (true) {
            System.out.println("Escolha o tipo de Pet");
            switch (sc.nextLine().trim()) {
                case "1" -> {
                    return Tipo.GATO;
                }
                case "2" -> {
                    return Tipo.CACHORRO;
                }
                default -> System.out.println("Opção inválida");
            }
        }

    }


}
