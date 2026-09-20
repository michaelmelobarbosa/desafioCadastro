package app;

import io.LeitorDeArquivo;

import java.io.FileNotFoundException;

public class Main {
    static void main(String[] args) throws FileNotFoundException {
        String path = "formulario.txt";

        LeitorDeArquivo leitorDeArquivo = new LeitorDeArquivo();
        leitorDeArquivo.ler(path);
    }
}
