package CommandManagement;

import entity.FlatCollection;

/**
 * Команда удаления элемента по его id
 */
public class RemoveByIdCommand implements Command {
    private final FlatCollection collection;
    
    /**
     * Конструктор команды remove_by_id
     * @param collection коллекция, из которой будет удален элемент
     */
    public RemoveByIdCommand(FlatCollection collection) {
        this.collection = collection;
    }
    
    @Override
    public String execute(String[] args) {
        if (args.length < 1) {
            return "Использование: remove_by_id <id>";
        }
        
        try {
            int id = Integer.parseInt(args[0]);
            boolean removed = collection.removeById(id);
            
            if (removed) {
                return "Элемент с id=" + id + " успешно удален";
            } else {
                return "Элемент с id=" + id + " не найден";
            }
        } catch (NumberFormatException e) {
            return "Ошибка: id должен быть целым числом";
        } catch (Exception e) {
            return "Ошибка при удалении элемента: " + e.getMessage();
        }
    }
    
    @Override
    public String getName() {
        return "remove_by_id";
    }
    
    @Override
    public String getDescription() {
        return "удалить элемент из коллекции по его id";
    }
} 