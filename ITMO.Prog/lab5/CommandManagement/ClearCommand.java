package CommandManagement;

import entity.FlatCollection;

/**
 * Команда очистки коллекции
 */
public class ClearCommand implements Command {
    private final FlatCollection collection;
    
    /**
     * Конструктор команды clear
     * @param collection коллекция для очистки
     */
    public ClearCommand(FlatCollection collection) {
        this.collection = collection;
    }
    
    @Override
    public String execute(String[] args) {
        collection.clear();
        return "Коллекция очищена";
    }
    
    @Override
    public String getName() {
        return "clear";
    }
    
    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }
} 