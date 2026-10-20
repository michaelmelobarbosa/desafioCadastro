package repository;

import model.Endereco;
import model.Pet;
import model.PetDto;
import model.PetRegistro;
import util.EscritorDeArquivo;
import util.LeitorDeArquivo;
import util.TextNormalizer;

import java.io.File;
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

    public List<PetRegistro> listaDeRegistros() {
        List<PetRegistro> registros = new ArrayList<>();

        for (String path : leitor.listOfPaths()) {
            registros.add(new PetRegistro(pathToPet(path), path));
        }
        return registros;
    }

    public List<Pet> listaDePets() {
        return listaDeRegistros().stream()
                .map(PetRegistro::pet)
                .toList();
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

    public List<PetRegistro> buscaPet(PetDto f) {
        return listaDeRegistros().stream()
                .filter(r -> f.getNome() == null
                        || contem(r.pet().getNome(), f.getNome())
                        || contem(r.pet().getSobrenome(), f.getNome()))
                .filter(r -> f.getTipo() == null || r.pet().getTipo() == f.getTipo())
                .filter(r -> f.getSexo() == null || r.pet().getSexo() == f.getSexo())
                .filter(r -> f.getIdade() == null || f.getIdade().equals(r.pet().getIdade()))
                .filter(r -> f.getPeso() == null || f.getPeso().equals(r.pet().getPeso()))
                .filter(r -> f.getRaca() == null || contem(r.pet().getRaca(), f.getRaca()))
                .filter(r -> f.getEndereco() == null
                        || contemEndereco(r.pet().getEndereco(), f.getEndereco()))
                .toList();
    }

    public boolean deletePet(String path) {
        File file = new File(path);
        return file.isFile() && file.delete();
    }

    private Pet pathToPet(String path) {
        List<String> linhas = leitor.fileToString(path);

        if (linhas.size() < 7) {
            throw new IllegalArgumentException(
                    "O arquivo não contém os dados completos de um pet: " + path
            );
        }

        return leitor.listOfPetsOutput(linhas);
    }
}
