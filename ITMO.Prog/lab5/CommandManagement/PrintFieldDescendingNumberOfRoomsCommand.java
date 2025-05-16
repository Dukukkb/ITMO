package CommandManagement;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

import entity.Flat;
import entity.FlatCollection;

/**
 * Команда вывода значений поля numberOfRooms всех элементов в порядке убывания
 */
public class PrintFieldDescendingNumberOfRoomsCommand implements Command {
    private final FlatCollection collection;
    
    /**
     * Конструктор команды print_field_descending_number_of_rooms
     * @param collection коллекция для вывода значений поля
     */
    public PrintFieldDescendingNumberOfRoomsCommand(FlatCollection collection) {
        this.collection = collection;
    }
    
    @Override
    public String execute(String[] args) {
        LinkedList<Flat> flats = collection.getFlats();
        
        if (flats.isEmpty()) {
            return "Коллекция пуста";
        }
        
        // Сортируем квартиры по убыванию количества комнат
        LinkedList<Long> sortedRooms = flats.stream()
                .map(Flat::getNumberOfRooms)
                .filter(rooms -> rooms != null)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toCollection(LinkedList::new));
        
        if (sortedRooms.isEmpty()) {
            return "Нет элементов с непустым полем numberOfRooms";
        }
        
        StringBuilder result = new StringBuilder("Значения поля numberOfRooms в порядке убывания:\n");
        
        for (Long numberOfRooms : sortedRooms) {
            result.append(numberOfRooms).append("\n");
        }
        
        return result.toString().trim();
    }
    
    @Override
    public String getName() {
        return "print_field_descending_number_of_rooms";
    }
    
    @Override
    public String getDescription() {
        return "вывести значения поля numberOfRooms всех элементов в порядке убывания";
    }
} 