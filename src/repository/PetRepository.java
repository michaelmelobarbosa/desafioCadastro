package repository;

import model.Pet;
import util.EscritorDeArquivo;

public class PetRepository {
    private final EscritorDeArquivo escritorDeArquivo;

    public PetRepository (EscritorDeArquivo escritorDeArquivo){
        this.escritorDeArquivo = escritorDeArquivo;
    }

    public void cadastrar(Pet pet) {

        String path = escritorDeArquivo.outputFormatter(pet);

        escritorDeArquivo.escrever(pet, path);

    }

}
