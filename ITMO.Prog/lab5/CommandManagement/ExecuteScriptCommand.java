package CommandManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import entity.FlatCollection;

/**
 * Команда выполнения скрипта из файла
 */
public class ExecuteScriptCommand implements Command {
    private final CommandManager commandManager;
    private final FlatCollection collection;
    private static final Set<String> executingScripts = new HashSet<>();
    
    /**
     * Конструктор команды execute_script
     * @param commandManager менеджер команд для выполнения команд из скрипта
     * @param collection коллекция, над которой выполняются команды
     */
    public ExecuteScriptCommand(CommandManager commandManager, FlatCollection collection) {
        this.commandManager = commandManager;
        this.collection = collection;
    }
    
    @Override
    public String execute(String[] args) {
        if (args.length < 1) {
            return "Использование: execute_script <file_name>";
        }
        
        String fileName = args[0];
        
        // Проверка на рекурсию
        if (executingScripts.contains(fileName)) {
            return "Обнаружена рекурсия: скрипт " + fileName + " уже выполняется";
        }
        
        try {
            executingScripts.add(fileName);
            StringBuilder result = new StringBuilder("Выполнение скрипта " + fileName + ":\n\n");
            
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                int lineNumber = 0;
                
                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    line = line.trim();
                    
                    if (line.isEmpty() || line.startsWith("#")) {
                        continue; // Пропускаем пустые строки и комментарии
                    }
                    
                    result.append("Строка ").append(lineNumber).append(": ").append(line).append("\n");
                    
                    // Разбиваем строку на команду и аргументы
                    String[] parts = line.split("\\s+", 2);
                    String commandName = parts[0];
                    String[] commandArgs = parts.length > 1 ? parts[1].split("\\s+") : new String[0];
                    
                    // Выполняем команду
                    String commandResult = commandManager.executeCommand(commandName, commandArgs);
                    result.append("Результат: ").append(commandResult).append("\n\n");
                }
                
                return result.toString();
            } finally {
                executingScripts.remove(fileName);
            }
        } catch (IOException e) {
            return "Ошибка при чтении скрипта: " + e.getMessage();
        }
    }
    
    @Override
    public String getName() {
        return "execute_script";
    }
    
    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла";
    }
} 