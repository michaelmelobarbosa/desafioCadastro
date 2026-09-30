package app;

import controller.PetController;
import repository.PetRepository;
import service.PetService;
import util.EscritorDeArquivo;

import java.io.FileNotFoundException;

public class Main {
    static void main(String[] args)  {
        EscritorDeArquivo escritorDeArquivo = new EscritorDeArquivo();
        PetRepository repository = new PetRepository(escritorDeArquivo);
        PetService service = new PetService(repository);
        PetController controller = new PetController(service);

        controller.cadastrar();


    }
}
