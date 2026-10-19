package controller;

import enums.Sexo;
import enums.Tipo;
import exceptions.ValidationException;
import model.Endereco;
import model.Pet;
import model.PetDto;
import service.PetService;
import util.TextNormalizer;

import java.util.List;
import java.util.Scanner;

public class PetController {
    private final Scanner sc = new Scanner(System.in);
    private final PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    public void cadastrar() {
        Endereco endereco = new Endereco();
        Pet pet = new Pet();

        System.out.print("1: ");
        pet.setNome(sc.nextLine());
        pet.setSobrenome(sc.nextLine());

        System.out.print("2: ");
        String tipo = sc.nextLine();
        if (tipo.equalsIgnoreCase("cachorro")) {
            pet.setTipo(Tipo.CACHORRO);
        } else if (tipo.equalsIgnoreCase("gato")) {
            pet.setTipo(Tipo.GATO);
        } else {
            System.out.println("Tipo indefinido");
        }

        System.out.print("3: ");
        String sexo = sc.nextLine();

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
        endereco.setRua(sc.nextLine());
        System.out.print("Número: ");
        endereco.setNumero(sc.nextLine());
        System.out.print("Cidade: ");
        endereco.setCidade(sc.nextLine());
        pet.setEndereco(endereco);

        System.out.print("5: ");
        pet.setIdade(lerDouble("a idade"));

        System.out.print("6: ");
        pet.setPeso(lerDouble("a peso"));

        System.out.print("7: ");
        pet.setRaca(sc.nextLine());

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
        Sexo sexo = null;
        Double idade = null;
        Double peso = null;
        String raca = null;
        String endereco = null;


        Tipo tipo = escolhaTipo();
        String escolhas = lerEscolhasBusca();

        for (int i = 0; i < escolhas.length(); i++) {
            switch (escolhas.charAt(i)) {
                case '1' -> nomeESobrenome = lerEntrada("o nome ou sobrenome: ");
                case '2' -> sexo = lerSexo("o sexo do pet: ");
                case '3' -> idade = lerDouble("a idade do pet: ");
                case '4' -> peso = lerDouble("o peso do pet: ");
                case '5' -> raca = lerEntrada("a raça: ");
                case '6' -> endereco = lerEntrada("o endereço: ");
                default -> throw new IllegalStateException("Entrada inválida");
            }
        }

        PetDto pet = new PetDto(nomeESobrenome, tipo, sexo, idade, peso, raca, endereco);

        return service.buscaPet(pet);
    }

    private static void mostrarMenuBusca() {
        System.out.println("Escolha de 1 a 6 (máximo 2) entre os critérios abaixo: ('Tipo' é um critério obrigatório)");
        System.out.println("""
                1. Nome ou sobrenome
                2. Sexo
                3. Idade
                4. Peso
                5. Raça
                6. Endereço
                """);
    }

    private String lerEscolhasBusca() {
        while (true) {
            mostrarMenuBusca();
            System.out.print("Digite um ou dois critérios (ex.: 16): ");

            String escolhas = sc.nextLine().replaceAll("\\s+", "");
            boolean formatoValido = escolhas.matches("[1-6]{1,2}");
            boolean semDuplicatas =
                    escolhas.chars().distinct().count() == escolhas.length();

            if (formatoValido && semDuplicatas) {
                return escolhas;
            }

            System.out.println("Escolha de 1 a 2 critérios diferentes, usando números de 1 a 6.");
        }
    }

    private Tipo escolhaTipo() {
        while (true) {
            System.out.println("Escolha o tipo de Pet (1-gato | 2-cachorro): ");
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

    private Double lerDouble(String message) {
        while (true) {
            System.out.println(message);
            String inputString = sc.nextLine().trim().replace(",", ".");
            try {
                Double input = Double.parseDouble(inputString);

                if (!validDouble(input)) {
                    System.out.println("Valor não pode ser menor que zero nem ser infinito.");
                    continue;
                }
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Exemplos de entradas válidas: '2' ou '1,2'");
            }
        }
    }

    private Sexo parseSexo(String valor) {
        return switch (TextNormalizer.normalize(valor)) {
            case "macho" -> Sexo.MACHO;
            case "femea" -> Sexo.FEMEA;
            default -> throw new ValidationException("Sexo", "Sexo Inválido");
        };
    }

    private Sexo lerSexo(String message) {
        while (true) {
            System.out.println(message);
            String sexo = sc.nextLine().trim();
            try {
                return parseSexo(sexo);
            } catch (ValidationException e) {
                System.out.println("Sexo inválido. Entradas válidas: femea ou macho");
            }
        }
    }

    private String lerEntrada(String message) {
        while (true) {
            System.out.println(message);
            String entrada = sc.nextLine().trim();
            if (!entrada.isBlank()) {
                return entrada.isBlank() ? null : entrada.trim();
            }
            System.out.println("Entrada não pode ser vazia. Tente novamente.");
        }
    }

    private boolean validDouble(Double input) {
        return Double.isFinite(input) && input >= 0;
    }
}
