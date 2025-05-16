package CommandManagement;

import java.util.Map;

/**
 * Команда вывода справки по доступным командам
 */
public class HelpCommand implements Command {
    private final Map<String, Command> commands;
    
    /**
     * Конструктор команды help
     * @param commands map со всеми доступными командами
     */
    public HelpCommand(Map<String, Command> commands) {
        this.commands = commands;
    }
    
    @Override
    public String execute(String[] args) {
        StringBuilder help = new StringBuilder("Доступные команды:\n");
        
        // Добавляем информацию о текущей команде, так как она не будет в map при первом вызове
        help.append(String.format("%-30s : %s%n", getName(), getDescription()));
        
        // Добавляем информацию о других командах
        for (Command command : commands.values()) {
            help.append(String.format("%-30s : %s%n", command.getName(), command.getDescription()));
        }
        
        return help.toString();
    }
    
    @Override
    public String getName() {
        return "help";
    }
    
    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }
} 