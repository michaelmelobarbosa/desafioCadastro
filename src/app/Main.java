package app;

import controller.PetController;
import model.PetRegistro;
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

        //-------------------------------------------------------------

        List<PetRegistro> pets = controller.buscaPet();
        controller.printList(pets);
    }
}
