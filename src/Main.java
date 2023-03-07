import model.store.PrizeReader;
import model.store.PrizeWriter;
import model.store.ToyStore;

public class Main {
    public static void main(String[] args) {
        PrizeWriter prizeWriter = new PrizeWriter();
        PrizeReader prizeReader = new PrizeReader();
        ToyStore toyStore = new ToyStore(prizeWriter, prizeReader);
        Controller controller = new Controller(toyStore);
        View view = new View(controller);
        view.run();
    }
}
