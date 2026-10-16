package app;

import controller.PetController;
import model.Pet;
import model.PetDto;
import repository.PetRepository;
import service.PetService;
import util.EscritorDeArquivo;
import util.LeitorDeArquivo;

import java.util.List;

public class Main {
    static void main(String[] args) {
        EscritorDeArquivo escritor = new EscritorDeArquivo();
        LeitorDeArquivo leitor = new LeitorDeArquivo();
        PetDto petDto = new PetDto();

        PetRepository repository = new PetRepository(escritor, leitor);
        PetService service = new PetService(repository);
        PetController controller = new PetController(service, petDto);

        //-------------------------------------------------------------

        List<Pet> pets = controller.buscaPet()  ;
        controller.printList(pets);
    }
}
