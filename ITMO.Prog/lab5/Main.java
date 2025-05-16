import java.io.IOException;
import java.util.Scanner;

import CommandManagement.CommandManager;
import entity.FlatCollection;
import entity.JsonManager;

public class Main {
	public static void main(String[] args) {
		try {
			// Инициализация коллекции
			FlatCollection collection = new FlatCollection();
			
			// Инициализация JsonManager для загрузки/сохранения данных
			JsonManager jsonManager = new JsonManager();
			
			// Получение имени файла из переменной окружения
			String fileName = System.getenv("LAB5_JSON_PATH");
			if (fileName == null || fileName.isEmpty()) {
				System.out.println("Переменная окружения LAB5_JSON_PATH не задана. Будет создана пустая коллекция.");
			} else {
				try {
					// Загрузка данных из файла
					collection = jsonManager.loadFromFile(fileName);
					System.out.println("Данные успешно загружены из файла: " + fileName);
				} catch (IOException e) {
					System.out.println("Ошибка при загрузке данных из файла: " + e.getMessage());
					System.out.println("Будет создана пустая коллекция.");
				}
			}
			
			CommandManager commandManager = new CommandManager(collection);
			
			// Запуск интерактивного режима
			Scanner scanner = new Scanner(System.in);
			System.out.println("Приложение для управления коллекцией Flat запущено.");
			System.out.println("Введите 'help' для получения справки по доступным командам.");
			
			while (true) {
				System.out.print("> ");
				String input;
				
				try {
					if (!scanner.hasNextLine()) {
						System.out.println("Завершение программы (получен сигнал EOF)");
						break;
					}
					input = scanner.nextLine().trim();
				} catch (Exception e) {
					System.out.println("Завершение программы");
					break;
				}
				
				if (input.isEmpty()) {
					continue;
				}
				
				String[] parts = input.split("\\s+", 2);
				String commandName = parts[0];
				String[] commandArgs = parts.length > 1 ? parts[1].split("\\s+") : new String[0];
				
				String result = commandManager.executeCommand(commandName, commandArgs);
				System.out.println(result);
			}
		} catch (Exception e) {
			System.err.println("Критическая ошибка: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
