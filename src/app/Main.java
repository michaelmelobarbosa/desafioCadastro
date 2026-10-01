package app;

import controller.PetController;
import model.Pet;
import repository.PetRepository;
import service.PetService;
import util.EscritorDeArquivo;
import util.LeitorDeArquivo;

import java.util.List;

public class Main {
    static void main(String[] args) {
        EscritorDeArquivo escritor = new EscritorDeArquivo();
        LeitorDeArquivo leitor = new LeitorDeArquivo();

        PetRepository repository = new PetRepository(escritor, leitor);
        PetService service = new PetService(repository);
        PetController controller = new PetController(service);

        List<Pet> pets = controller.buscarPorNome("na");
        controller.printList(pets);


    }
}
