package CommandManagement;

import java.util.Comparator;
import java.util.LinkedList;

import entity.Flat;
import entity.FlatCollection;

/**
 * Команда вывода объекта с минимальным значением поля transport
 */
public class MinByTransportCommand implements Command {
    private final FlatCollection collection;
    
    /**
     * Конструктор команды min_by_transport
     * @param collection коллекция для поиска
     */
    public MinByTransportCommand(FlatCollection collection) {
        this.collection = collection;
    }
    
    @Override
    public String execute(String[] args) {
        LinkedList<Flat> flats = collection.getFlats();
        
        if (flats.isEmpty()) {
            return "Коллекция пуста";
        }
        
        // Создаем компаратор по полю transport
        Comparator<Flat> transportComparator = Comparator.comparing(
            flat -> flat.getTransport() != null ? flat.getTransport().ordinal() : Integer.MAX_VALUE
        );
        
        Flat minFlat = flats.stream()
                .filter(flat -> flat.getTransport() != null)
                .min(transportComparator)
                .orElse(null);
        
        if (minFlat == null) {
            return "В коллекции нет элементов с непустым полем transport";
        }
        
        return "Объект с минимальным значением поля transport:\n" + minFlat.toString();
    }
    
    @Override
    public String getName() {
        return "min_by_transport";
    }
    
    @Override
    public String getDescription() {
        return "вывести объект из коллекции, значение поля transport которого является минимальным";
    }
} 