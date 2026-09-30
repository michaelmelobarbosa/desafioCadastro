package service;

import model.Pet;
import repository.PetRepository;

public class PetService {
    PetRepository repository;

    public void cadastrar(Pet pet) {

        validate(pet);
        //ADICIONAR PETALREADYEXISTS EXTECPTION
        repository.cadastrar(pet);


    }

    public void validate(Pet pet) {

        if (pet == null) {
            throw new IllegalArgumentException("Pet não pode ser nulo");
        }

        //completar outras validações
    }
}
