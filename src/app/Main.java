package app;

import controller.PetController;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    static void main(String[] args) throws FileNotFoundException {

        PetController operador = new PetController();
        double idade = 5;

        List<String> porIdade = operador.buscaPorIdade(idade);
        operador.listarTodosPets(porIdade);
    }
}
