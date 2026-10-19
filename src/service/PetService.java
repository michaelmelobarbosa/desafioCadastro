package service;

import enums.Sexo;
import enums.Tipo;
import exceptions.ValidationException;
import model.Pet;
import model.PetDto;
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

        if (pet.getNome() == null || pet.getNome().isBlank()) {
            throw new ValidationException("nome", "nome é obrigatório.");
        }

        if (pet.getSobrenome() == null || pet.getSobrenome().isBlank()) {
            throw new ValidationException("nome", "nome é obrigatório.");
        }

        if (pet.getTipo() == null) {
            throw new ValidationException("tipo", "tipo é obrigatório.");
        }

        if (pet.getSexo() == null) {
            throw new ValidationException("sexo", "sexo é obrigatório.");
        }

        if (pet.getEndereco() == null) {
            throw new ValidationException("endereço", "endereço é obrigatório");
        }

        if (pet.getEndereco().getRua() == null || pet.getEndereco().getRua().isBlank()) {
            throw new ValidationException("rua", "rua é obrigatória");
        }

        if (pet.getEndereco().getNumero() == null || pet.getEndereco().getNumero().isBlank()) {
            throw new ValidationException("numero", "numero é obrigatório");
        }

        if (pet.getEndereco().getCidade() == null || pet.getEndereco().getCidade().isBlank()) {
            throw new ValidationException("cidade", "cidade é obrigatória");
        }

        if (pet.getIdade() == null || pet.getIdade() < 0 || !Double.isFinite(pet.getIdade())) {
            throw new ValidationException("idade", "idade inválida");
        }

        if (pet.getPeso() == null || pet.getPeso() < 0 || !Double.isFinite(pet.getPeso())) {
            throw new ValidationException("peso", "peso inválida");
        }

        if (pet.getRaca() == null) {
            throw new ValidationException("raça", "raça inválida");
        }
    }

    public List<Pet> listaDePets() {
        return repository.listaDePets();
    }

    public List<Pet> buscaPet(PetDto pet) {
        return repository.buscaPet(pet);
    }


}
