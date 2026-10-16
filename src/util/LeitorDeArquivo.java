package util;

import enums.Sexo;
import enums.Tipo;
import model.Endereco;
import model.Pet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LeitorDeArquivo {

    public List<String> fileToString(String path) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();

            while (line != null) {
                lines.add(line);
                line = buffer.readLine();
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lines;
    }

    public Pet listOfPetsOutput(List<String> list) {
        //tratamento de linha para retirar numeração inicial
        List<String> cleanedLines = new ArrayList<>();
        for (String line : list) {
            line = line.substring(4);
            cleanedLines.add(line);
        }

        // separar nome e sobrenome
        List<String> nomeESobrenome = List.of(cleanedLines.getFirst().split(" "));
        String nome = nomeESobrenome.getFirst();
        StringBuilder sobrenomeSB = new StringBuilder();

        for (int i = 1; i < nomeESobrenome.size(); i++) {
            sobrenomeSB.append(nomeESobrenome.get(i)).append(" ");
        }

        String sobrenome = sobrenomeSB.toString();
        Tipo tipo = Tipo.valueOf(cleanedLines.get(1).trim().toUpperCase());
        String sexoNormalizado = Normalizer
                .normalize(cleanedLines.get(2).trim(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toUpperCase(Locale.ROOT);

        Sexo sexo = Sexo.valueOf(sexoNormalizado);

        //tratamento linha do endereço
        List<String> enderecoCompleto = List.of(cleanedLines.get(3).split(", "));
        String rua = enderecoCompleto.getFirst();
        String numero = enderecoCompleto.get(1);
        String cidade = enderecoCompleto.get(2);

        String idade = cleanedLines.get(4);
        String peso = cleanedLines.get(5);
        String raca = cleanedLines.get(6);

        Endereco endereco = new Endereco(rua, numero, cidade);

        return new Pet(nome, sobrenome, tipo, sexo, endereco, Double.valueOf(idade), Double.valueOf(peso), raca);
    }

    public List<String> listOfPaths() {

        String strPath = "petsCadastrados";
        File path = new File(strPath);
        File[] files = path.listFiles();
        List<String> listOfPaths = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                listOfPaths.add(file.toString());
            }
        }
        return listOfPaths;
    }
}
