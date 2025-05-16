package CommandManagement;

/**
 * Команда выхода из программы
 */
public class ExitCommand implements Command {
    
    @Override
    public String execute(String[] args) {
        System.out.println("Выход из программы...");
        System.exit(0);
        return ""; // Никогда не будет выполнено из-за System.exit
    }
    
    @Override
    public String getName() {
        return "exit";
    }
    
    @Override
    public String getDescription() {
        return "завершить программу (без сохранения в файл)";
    }
} 