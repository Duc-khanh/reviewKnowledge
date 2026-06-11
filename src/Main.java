import repository.ContactRepository;
import service.ContactService;
import service.SearchService;
import service.StatisticsService;
import ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        ContactRepository repository = new ContactRepository();
        ContactService contactService = new ContactService(repository);
        SearchService searchService = new SearchService();
        StatisticsService statisticsService = new StatisticsService();
        
        ConsoleUI ui = new ConsoleUI(contactService, searchService, statisticsService);
        ui.start();
    }
}