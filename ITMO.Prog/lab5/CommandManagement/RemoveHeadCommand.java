package CommandManagement;

import java.util.LinkedList;

import entity.Flat;
import entity.FlatCollection;

/**
 * Команда вывода первого элемента и его удаления из коллекции
 */
public class RemoveHeadCommand implements Command {
    private final FlatCollection collection;
    
    /**
     * Конструктор команды remove_head
     * @param collection коллекция для работы с первым элементом
     */
    public RemoveHeadCommand(FlatCollection collection) {
        this.collection = collection;
    }
    
    @Override
    public String execute(String[] args) {
        LinkedList<Flat> flats = collection.getFlats();
        
        if (flats.isEmpty()) {
            return "Коллекция пуста, нечего удалять";
        }
        
        Flat firstFlat = flats.getFirst();
        String result = "Первый элемент коллекции:\n" + firstFlat.toString();
        
        flats.removeFirst();
        return result + "\n\nЭлемент успешно удален";
    }
    
    @Override
    public String getName() {
        return "remove_head";
    }
    
    @Override
    public String getDescription() {
        return "вывести первый элемент коллекции и удалить его";
    }
} 