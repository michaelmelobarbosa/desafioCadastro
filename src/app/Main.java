package app;

import controller.Operador;

import java.io.FileNotFoundException;

public class Main {
    static void main(String[] args) throws FileNotFoundException {

        Operador operador = new Operador();
        operador.listarPets();

    }
}
