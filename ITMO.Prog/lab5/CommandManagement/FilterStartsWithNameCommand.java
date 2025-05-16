package CommandManagement;

import java.util.LinkedList;
import java.util.stream.Collectors;

import entity.Flat;
import entity.FlatCollection;

/**
 * Команда вывода элементов, значение поля name которых начинается с заданной подстроки
 */
public class FilterStartsWithNameCommand implements Command {
    private final FlatCollection collection;
    
    /**
     * Конструктор команды filter_starts_with_name
     * @param collection коллекция для фильтрации
     */
    public FilterStartsWithNameCommand(FlatCollection collection) {
        this.collection = collection;
    }
    
    @Override
    public String execute(String[] args) {
        if (args.length < 1) {
            return "Использование: filter_starts_with_name <name>";
        }
        
        String prefix = args[0];
        LinkedList<Flat> flats = collection.getFlats();
        
        if (flats.isEmpty()) {
            return "Коллекция пуста";
        }
        
        LinkedList<Flat> filteredFlats = flats.stream()
                .filter(flat -> flat.getName() != null && flat.getName().startsWith(prefix))
                .collect(Collectors.toCollection(LinkedList::new));
        
        if (filteredFlats.isEmpty()) {
            return "Нет элементов, у которых значение поля name начинается с '" + prefix + "'";
        }
        
        StringBuilder result = new StringBuilder("Элементы, значение поля name которых начинается с '" + prefix + "':\n\n");
        
        for (Flat flat : filteredFlats) {
            result.append(flat.toString()).append("\n\n");
        }
        
        return result.toString().trim();
    }
    
    @Override
    public String getName() {
        return "filter_starts_with_name";
    }
    
    @Override
    public String getDescription() {
        return "вывести элементы, значение поля name которых начинается с заданной подстроки";
    }
} 