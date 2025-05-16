package CommandManagement;

import java.util.Scanner;

import entity.Coordinates;
import entity.Flat;
import entity.FlatCollection;
import entity.Furnish;
import entity.House;
import entity.Transport;
import entity.View;

/**
 * Команда добавления нового элемента в коллекцию
 */
public class AddCommand implements Command {
    private final FlatCollection collection;
    
    /**
     * Конструктор команды add
     * @param collection коллекция для добавления элемента
     */
    public AddCommand(FlatCollection collection) {
        this.collection = collection;
    }
    
    @Override
    public String execute(String[] args) {
        try {
            Flat flat = createFlatFromUserInput();
            collection.addFlat(flat);
            return "Квартира успешно добавлена с id=" + flat.getId();
        } catch (Exception e) {
            return "Ошибка при добавлении квартиры: " + e.getMessage();
        }
    }
    
    /**
     * Создает новый объект Flat из пользовательского ввода
     * @return созданный объект Flat
     */
    private Flat createFlatFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        Flat flat = new Flat();
        
        // Имя квартиры
        String name = "";
        while (name.isEmpty()) {
            System.out.print("Введите название квартиры: ");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Название не может быть пустым. Пожалуйста, введите снова.");
            }
        }
        flat.setName(name);
        
        // Создание координат
        Float x = null;
        while (x == null) {
            try {
                System.out.print("Введите координату X (> -262): ");
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Значение не может быть пустым. Пожалуйста, введите число.");
                    continue;
                }
                x = Float.parseFloat(input);
                if (x <= -262) {
                    System.out.println("Значение X должно быть больше -262.");
                    x = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формат числа. Пожалуйста, введите корректное значение.");
            }
        }
        
        Double y = null;
        while (y == null) {
            try {
                System.out.print("Введите координату Y: ");
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Значение не может быть пустым. Пожалуйста, введите число.");
                    continue;
                }
                y = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формат числа. Пожалуйста, введите корректное значение.");
            }
        }
        flat.setCoordinates(new Coordinates(x, y));
        
        // Площадь
        Integer area = null;
        while (area == null) {
            try {
                System.out.print("Введите площадь (> 0, <= 597): ");
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Значение не может быть пустым. Пожалуйста, введите число.");
                    continue;
                }
                area = Integer.parseInt(input);
                if (area <= 0 || area > 597) {
                    System.out.println("Площадь должна быть больше 0 и не больше 597.");
                    area = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формат числа. Пожалуйста, введите корректное значение.");
            }
        }
        flat.setArea(area);
        
        // Количество комнат
        Long numberOfRooms = null;
        while (numberOfRooms == null) {
            try {
                System.out.print("Введите количество комнат (> 0): ");
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Значение не может быть пустым. Пожалуйста, введите число.");
                    continue;
                }
                numberOfRooms = Long.parseLong(input);
                if (numberOfRooms <= 0) {
                    System.out.println("Количество комнат должно быть больше 0.");
                    numberOfRooms = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формат числа. Пожалуйста, введите корректное значение.");
            }
        }
        flat.setNumberOfRooms(numberOfRooms);
        
        // Мебель (опционально)
        boolean validFurnish = false;
        while (!validFurnish) {
            try {
                System.out.print("Выберите мебель (DESIGNER/NONE/LITTLE или пустая строка для null): ");
                String furnishStr = scanner.nextLine();
                if (furnishStr.isEmpty()) {
                    validFurnish = true;
                } else {
                    flat.setFurnish(Furnish.valueOf(furnishStr.toUpperCase()));
                    validFurnish = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Неверное значение. Допустимые значения: DESIGNER, NONE, LITTLE.");
            }
        }
        
        // Вид (опционально)
        boolean validView = false;
        while (!validView) {
            try {
                System.out.print("Выберите вид (PARK/BAD/NORMAL/GOOD/TERRIBLE или пустая строка для null): ");
                String viewStr = scanner.nextLine();
                if (viewStr.isEmpty()) {
                    validView = true;
                } else {
                    flat.setView(View.valueOf(viewStr.toUpperCase()));
                    validView = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Неверное значение. Допустимые значения: PARK, BAD, NORMAL, GOOD, TERRIBLE.");
            }
        }
        
        // Транспорт (опционально)
        boolean validTransport = false;
        while (!validTransport) {
            try {
                System.out.print("Выберите транспорт (FEW/NONE/ENOUGH или пустая строка для null): ");
                String transportStr = scanner.nextLine();
                if (transportStr.isEmpty()) {
                    validTransport = true;
                } else {
                    flat.setTransport(Transport.valueOf(transportStr.toUpperCase()));
                    validTransport = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Неверное значение. Допустимые значения: FEW, NONE, ENOUGH.");
            }
        }
        
        // Создание дома
        String houseName = "";
        while (houseName.isEmpty()) {
            System.out.print("Введите название дома: ");
            houseName = scanner.nextLine();
            if (houseName.isEmpty()) {
                System.out.println("Название дома не может быть пустым. Пожалуйста, введите снова.");
            }
        }
        
        int year = 0;
        while (year <= 0) {
            try {
                System.out.print("Введите год постройки (> 0): ");
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Значение не может быть пустым. Пожалуйста, введите число.");
                    continue;
                }
                year = Integer.parseInt(input);
                if (year <= 0) {
                    System.out.println("Год постройки должен быть больше 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формат числа. Пожалуйста, введите корректное значение.");
            }
        }
        
        Integer floors = null;
        while (floors == null || floors <= 0) {
            try {
                System.out.print("Введите количество этажей (> 0): ");
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Значение не может быть пустым. Пожалуйста, введите число.");
                    continue;
                }
                floors = Integer.parseInt(input);
                if (floors <= 0) {
                    System.out.println("Количество этажей должно быть больше 0.");
                    floors = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формат числа. Пожалуйста, введите корректное значение.");
            }
        }
        
        int flatsOnFloor = 0;
        while (flatsOnFloor <= 0) {
            try {
                System.out.print("Введите количество квартир на этаже (> 0): ");
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Значение не может быть пустым. Пожалуйста, введите число.");
                    continue;
                }
                flatsOnFloor = Integer.parseInt(input);
                if (flatsOnFloor <= 0) {
                    System.out.println("Количество квартир на этаже должно быть больше 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формат числа. Пожалуйста, введите корректное значение.");
            }
        }
        
        flat.setHouse(new House(houseName, year, floors, flatsOnFloor));
        
        return flat;
    }
    
    @Override
    public String getName() {
        return "add";
    }
    
    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }
} 