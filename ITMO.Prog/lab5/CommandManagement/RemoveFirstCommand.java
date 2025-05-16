package CommandManagement;

import java.util.LinkedList;

import entity.Flat;
import entity.FlatCollection;

/**
 * Команда удаления первого элемента из коллекции
 */
public class RemoveFirstCommand implements Command {
    private final FlatCollection collection;
    
    /**
     * Конструктор команды remove_first
     * @param collection коллекция для удаления элемента
     */
    public RemoveFirstCommand(FlatCollection collection) {
        this.collection = collection;
    }
    
    @Override
    public String execute(String[] args) {
        LinkedList<Flat> flats = collection.getFlats();
        
        if (flats.isEmpty()) {
            return "Коллекция пуста, нечего удалять";
        }
        
        Flat removedFlat = flats.removeFirst();
        return "Первый элемент успешно удален: " + removedFlat.getId();
    }
    
    @Override
    public String getName() {
        return "remove_first";
    }
    
    @Override
    public String getDescription() {
        return "удалить первый элемент из коллекции";
    }
} 