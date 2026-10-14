package repository;

import model.Endereco;
import model.Pet;
import model.PetDto;
import util.EscritorDeArquivo;
import util.LeitorDeArquivo;
import util.TextNormalizer;

import java.util.ArrayList;
import java.util.List;

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

    private boolean contem(String petCadastrado, String entrada) {
        if (petCadastrado == null) return false;
        String a = TextNormalizer.normalize(petCadastrado);
        String b = TextNormalizer.normalize(entrada);
        return a.contains(b);
    }

    private boolean contemEndereco(Endereco endereco, String busca) {
        if (endereco == null) return false;
        return contem(endereco.getRua(), busca)
                || contem(endereco.getNumero(), busca)
                || contem(endereco.getCidade(), busca);
    }

    public List<Pet> buscaPet(PetDto f) {
        List<Pet> list = listaDePets();

        return list.stream()
                .filter(p -> f.getNome() == null || contem(p.getNome(), f.getNome()))
                .filter(p -> f.getTipo() == null || p.getTipo() == f.getTipo())
                .filter(p -> f.getSexo() == null || p.getSexo() == f.getSexo())
                .filter(p -> f.getIdade() == null || p.getIdade().equals(f.getIdade()))
                .filter(p -> f.getPeso() == null || p.getPeso().equals(f.getPeso()))
                .filter(p -> f.getRaca() == null || contem(p.getRaca(), f.getRaca()))
                .filter(p -> f.getEndereco() == null || contemEndereco(p.getEndereco(), f.getEndereco()))
                .toList();
    }

}
