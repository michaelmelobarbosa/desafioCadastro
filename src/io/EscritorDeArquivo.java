package io;

import enums.Sexo;
import enums.Tipo;
import model.Pet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EscritorDeArquivo {

    public void escrever(Pet pet, String path) {
        List<String> petLista = petParaLista(pet);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            bufferedWriter.write("1 - " + petLista.getFirst() + " " + petLista.get(1));
            bufferedWriter.newLine();

            for (int i = 2; i < petLista.size(); i++) {
                bufferedWriter.write((i) + " - " + petLista.get(i));
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<String> petParaLista(Pet pet) {

        List<String> listaPet = new ArrayList<>();
        listaPet.add(pet.getNome());
        listaPet.add(pet.getSobrenome());
        if (pet.getTipo() == Tipo.CACHORRO) {
            listaPet.add("cachorro");
        } else if (pet.getTipo() == Tipo.GATO) {
            listaPet.add("gato");
        }

        if (pet.getSexo() == Sexo.MACHO) {
            listaPet.add("macho");
        } else if (pet.getSexo() == Sexo.FEMEA) {
            listaPet.add("fêmea");
        }

        listaPet.add(String.join(", ", pet.getEnderecoComoLista()));
        listaPet.add(String.valueOf(pet.getIdade()));
        listaPet.add(String.valueOf(pet.getPeso()));
        listaPet.add(pet.getRaca());

        return listaPet;
    }

    public String outputFormatter(Pet pet) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm"));
        return "petsCadastrados/" + date + "-" + pet.getNome().toUpperCase() + pet.getSobrenome().toUpperCase() + ".TXT";
    }
}
