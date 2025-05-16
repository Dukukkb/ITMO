package CommandManagement;

import java.time.format.DateTimeFormatter;

import entity.FlatCollection;

/**
 * Команда вывода информации о коллекции
 */
public class InfoCommand implements Command {
    private final FlatCollection collection;
    
    /**
     * Конструктор команды info
     * @param collection коллекция, о которой нужно вывести информацию
     */
    public InfoCommand(FlatCollection collection) {
        this.collection = collection;
    }
    
    @Override
    public String execute(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        
        return String.format(
            "Тип коллекции: %s\n" +
            "Дата инициализации: %s\n" +
            "Количество элементов: %d",
            "LinkedList<Flat>",
            collection.getInitializationDate().format(formatter),
            collection.getSize()
        );
    }
    
    @Override
    public String getName() {
        return "info";
    }
    
    @Override
    public String getDescription() {
        return "вывести информацию о коллекции (тип, дата инициализации, количество элементов)";
    }
} 