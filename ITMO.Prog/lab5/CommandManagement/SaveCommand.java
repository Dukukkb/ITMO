package CommandManagement;

import java.io.IOException;

import entity.FlatCollection;
import entity.JsonManager;

/**
 * Команда сохранения коллекции в файл
 */
public class SaveCommand implements Command {
    private final FlatCollection collection;
    private final JsonManager jsonManager;
    
    /**
     * Конструктор команды save
     * @param collection коллекция для сохранения в файл
     */
    public SaveCommand(FlatCollection collection) {
        this.collection = collection;
        this.jsonManager = new JsonManager();
    }
    
    @Override
    public String execute(String[] args) {
        String fileName = System.getenv("LAB5_JSON_PATH");
        if (fileName == null || fileName.isEmpty()) {
            return "Ошибка: переменная окружения LAB5_JSON_PATH не задана";
        }
        
        try {
            jsonManager.saveToFile(collection, fileName);
            return "Коллекция успешно сохранена в файл: " + fileName;
        } catch (IOException e) {
            return "Ошибка при сохранении коллекции: " + e.getMessage();
        }
    }
    
    @Override
    public String getName() {
        return "save";
    }
    
    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }
} 