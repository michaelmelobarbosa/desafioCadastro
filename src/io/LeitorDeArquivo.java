package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeitorDeArquivo {

    public String ler(String path) throws FileNotFoundException {

        try(BufferedReader buffer = new BufferedReader(new FileReader(path))){
            String line = buffer.readLine();

            while(line != null){
                System.out.println(line);
                line = buffer.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return path;
    }
}
