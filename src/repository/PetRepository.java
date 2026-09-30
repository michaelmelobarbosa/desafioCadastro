package repository;

import model.Pet;

public class PetRepository {
    Escritor escritor = new Escritor();


    public void cadastrar(Pet pet){

        

        String path = escritor.outputFormatter(pet);

        escritor.escrever(pet, path);
    }

}
