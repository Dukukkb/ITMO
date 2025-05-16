package CommandManagement;

import entity.Flat;
import entity.FlatCollection;

/**
 * Команда вывода всех элементов коллекции
 */
public class ShowCommand implements Command {
    private final FlatCollection collection;
    
    /**
     * Конструктор команды show
     * @param collection коллекция для вывода
     */
    public ShowCommand(FlatCollection collection) {
        this.collection = collection;
    }
    
    @Override
    public String execute(String[] args) {
        if (collection.isEmpty()) {
            return "Коллекция пуста";
        }
        
        StringBuilder result = new StringBuilder();
        for (Flat flat : collection.getFlats()) {
            result.append(flat.toString()).append("\n\n");
        }
        
        return result.toString().trim();
    }
    
    @Override
    public String getName() {
        return "show";
    }
    
    @Override
    public String getDescription() {
        return "вывести все элементы коллекции в строковом представлении";
    }
} 