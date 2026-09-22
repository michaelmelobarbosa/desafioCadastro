package app;

import controller.Operador;

import java.io.FileNotFoundException;

public class Main {
    static void main(String[] args) throws FileNotFoundException {


//        MenuPrincipal menuPrincipal = new MenuPrincipal();
//        menuPrincipal.mostrar();

        Operador op =new Operador();
        op.cadastrar();

    }
}
