package repository;

import model.Pet;
import util.EscritorDeArquivo;
import util.LeitorDeArquivo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PetRepository {
    private final EscritorDeArquivo escritorDeArquivo;
    private final LeitorDeArquivo leitor;

    public PetRepository(EscritorDeArquivo escritorDeArquivo, LeitorDeArquivo leitor) {
        this.escritorDeArquivo = escritorDeArquivo;
        this.leitor = leitor;
    }

    public void cadastrar(Pet pet) {
        String path = escritorDeArquivo.outputFormatter(pet);
        escritorDeArquivo.escrever(pet, path);

    }

    public List<Pet> listaDePets() {

        List<String> listaPaths = leitor.listOfPaths();
        List<Pet> listaPets = new ArrayList<>();

        for (String listaPath : listaPaths) {
            List<String> strings = leitor.fileToString(listaPath);
            Pet pet = leitor.listOfPetsOutput(strings);
            listaPets.add(pet);
        }
        return listaPets;
    }

    public void listarPets() {
        List<Pet> list = listaDePets();
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    public List<Pet> buscarPorNome(String nome) {
        List<Pet> list = listaDePets();

        return list.stream().filter(p -> p.getNome().contains(nome)).toList();
    }

}
