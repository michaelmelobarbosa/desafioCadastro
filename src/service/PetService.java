package service;

import exceptions.ValidationException;
import model.Pet;
import repository.PetRepository;

import java.util.List;

public class PetService {
    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Pet pet) {
        validate(pet);
        repository.cadastrar(pet);
    }

    public void validate(Pet pet) {

        if (pet == null) {
            throw new IllegalArgumentException("Pet não pode ser nulo");
        }

        if(pet.getNome() == null || pet.getNome().isBlank()) {
            throw new ValidationException("nome", "nome é obrigatório.");
        }

        if(pet.getSobrenome() == null || pet.getSobrenome().isBlank()) {
            throw new ValidationException("nome", "nome é obrigatório.");
        }

        if(pet.getTipo() == null) {
            throw new ValidationException("tipo", "tipo é obrigatório.");
        }

        if(pet.getSexo() == null) {
            throw new ValidationException("sexo", "sexo é obrigatório.");
        }

        if(pet.getEndereco().getRua() == null){
            throw new ValidationException("rua", "rua é obrigatória");
        }

        if(pet.getEndereco().getNumero() == null){
            throw new ValidationException("numero", "numero é obrigatório");
        }

        if(pet.getEndereco().getCidade() == null){
            throw new ValidationException("cidade", "cidade é obrigatória");
        }

        if(pet.getIdade() == null || pet.getIdade() < 0){
            throw new ValidationException("idade", "idade inválida");
        }

        if(pet.getPeso() == null || pet.getPeso() < 0){
            throw new ValidationException("peso", "peso inválida");
        }

        if(pet.getRaca() == null){
            throw new ValidationException("raça", "raça inválida");
        }
    }

    public void listarPets(){
        repository.listarPets();
    }

    public List<Pet> buscarPorNome(String nome){
        return repository.buscarPorNome(nome);
    }
}
