package CommandManagement;

/**
 * Интерфейс для всех команд консольного приложения
 */
public interface Command {
    /**
     * Выполняет команду с указанными аргументами
     * @param args аргументы команды
     * @return результат выполнения команды в виде строки
     */
    String execute(String[] args);
    
    /**
     * Возвращает имя команды
     * @return имя команды
     */
    String getName();
    
    /**
     * Возвращает описание команды
     * @return описание команды
     */
    String getDescription();
} 