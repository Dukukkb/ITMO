package CommandManagement;

import java.util.HashMap;
import java.util.Map;

import entity.FlatCollection;

/**
 * Класс для управления командами. Регистрирует все команды и направляет вызовы к соответствующим обработчикам.
 */
public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    private final FlatCollection collection;
    
    /**
     * Конструктор менеджера команд
     * @param collection коллекция квартир, с которой будут работать команды
     */
    public CommandManager(FlatCollection collection) {
        this.collection = collection;
        registerCommands();
    }
    
    /**
     * Регистрирует все доступные команды
     */
    private void registerCommands() {
        // Базовые команды
        registerCommand(new HelpCommand(commands));
        registerCommand(new InfoCommand(collection));
        registerCommand(new ShowCommand(collection));
        registerCommand(new AddCommand(collection));
        registerCommand(new UpdateCommand(collection));
        registerCommand(new RemoveByIdCommand(collection));
        registerCommand(new ClearCommand(collection));
        registerCommand(new SaveCommand(collection));
        
        // Специальные команды
        registerCommand(new ExecuteScriptCommand(this, collection));
        registerCommand(new ExitCommand());
        registerCommand(new RemoveFirstCommand(collection));
        registerCommand(new RemoveHeadCommand(collection));
        registerCommand(new AddIfMaxCommand(collection));
        registerCommand(new MinByTransportCommand(collection));
        registerCommand(new FilterStartsWithNameCommand(collection));
        registerCommand(new PrintFieldDescendingNumberOfRoomsCommand(collection));
    }
    
    /**
     * Регистрирует команду в системе
     * @param command команда для регистрации
     */
    private void registerCommand(Command command) {
        commands.put(command.getName(), command);
    }
    
    /**
     * Выполняет команду по имени с указанными аргументами
     * @param commandName имя команды
     * @param args аргументы команды
     * @return результат выполнения команды
     */
    public String executeCommand(String commandName, String[] args) {
        Command command = commands.get(commandName);
        if (command == null) {
            return "Неизвестная команда: " + commandName + ". Введите 'help' для получения списка доступных команд.";
        }
        try {
            return command.execute(args);
        } catch (Exception e) {
            return "Ошибка при выполнении команды: " + e.getMessage();
        }
    }
    
    /**
     * Возвращает map со всеми зарегистрированными командами
     * @return map с командами (ключ - имя команды, значение - объект команды)
     */
    public Map<String, Command> getCommands() {
        return commands;
    }
} 