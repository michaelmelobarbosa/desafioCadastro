package io;

import enums.Sexo;
import enums.Tipo;
import model.Pet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EscritorDeArquivo {
    String path = "petsCadastrados/out.txt";


    public void escrever(Pet pet) {
        List<String> petLista = petParaLista(pet);
        
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            bufferedWriter.write("1: " + petLista.getFirst() + " "+ petLista.get(1));
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

        listaPet.add(pet.getEnderecoComoLista().toString());
        listaPet.add(String.valueOf(pet.getIdade()));
        listaPet.add(String.valueOf(pet.getPeso()));
        listaPet.add(pet.getRaca());

        return listaPet;
    }

}
