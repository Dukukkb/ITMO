package CommandManagement;

import java.util.LinkedList;
import java.util.Scanner;

import entity.Coordinates;
import entity.Flat;
import entity.FlatCollection;
import entity.Furnish;
import entity.House;
import entity.Transport;
import entity.View;

/**
 * Команда добавления нового элемента, если его значение превышает значение наибольшего элемента коллекции
 */
public class AddIfMaxCommand implements Command {
    private final FlatCollection collection;
    private final AddCommand addCommand;
    
    /**
     * Конструктор команды add_if_max
     * @param collection коллекция для добавления элемента
     */
    public AddIfMaxCommand(FlatCollection collection) {
        this.collection = collection;
        this.addCommand = new AddCommand(collection);
    }
    
    @Override
    public String execute(String[] args) {
        try {
            System.out.println("Создание нового элемента для сравнения:");
            Flat newFlat = createFlatFromUserInput();
            
            LinkedList<Flat> flats = collection.getFlats();
            
            if (flats.isEmpty()) {
                collection.addFlat(newFlat);
                return "Коллекция была пуста. Элемент добавлен с id=" + newFlat.getId();
            }
            
            // Найдем максимальный элемент по количеству комнат
            Flat maxFlat = flats.stream()
                    .max(Flat.ROOMS_COMPARATOR)
                    .orElse(null);
            
            if (maxFlat == null || Flat.ROOMS_COMPARATOR.compare(newFlat, maxFlat) > 0) {
                collection.addFlat(newFlat);
                return "Элемент добавлен с id=" + newFlat.getId() + ", так как его значение больше максимального";
            } else {
                return "Элемент не добавлен, так как его значение не превышает максимальное в коллекции";
            }
        } catch (Exception e) {
            return "Ошибка при выполнении команды: " + e.getMessage();
        }
    }
    
    /**
     * Создает новый объект Flat из пользовательского ввода
     * @return созданный объект Flat
     */
    private Flat createFlatFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        Flat flat = new Flat();
        
        System.out.print("Введите название квартиры: ");
        flat.setName(scanner.nextLine());
        
        // Создание координат
        System.out.print("Введите координату X (> -262): ");
        Float x = Float.parseFloat(scanner.nextLine());
        System.out.print("Введите координату Y: ");
        Double y = Double.parseDouble(scanner.nextLine());
        flat.setCoordinates(new Coordinates(x, y));
        
        // Площадь
        System.out.print("Введите площадь (> 0, <= 597): ");
        flat.setArea(Integer.parseInt(scanner.nextLine()));
        
        // Количество комнат
        System.out.print("Введите количество комнат (> 0): ");
        flat.setNumberOfRooms(Long.parseLong(scanner.nextLine()));
        
        // Мебель (опционально)
        System.out.print("Выберите мебель (DESIGNER/NONE/LITTLE или пустая строка для null): ");
        String furnishStr = scanner.nextLine();
        if (!furnishStr.isEmpty()) {
            flat.setFurnish(Furnish.valueOf(furnishStr.toUpperCase()));
        }
        
        // Вид (опционально)
        System.out.print("Выберите вид (PARK/BAD/NORMAL/GOOD/TERRIBLE или пустая строка для null): ");
        String viewStr = scanner.nextLine();
        if (!viewStr.isEmpty()) {
            flat.setView(View.valueOf(viewStr.toUpperCase()));
        }
        
        // Транспорт (опционально)
        System.out.print("Выберите транспорт (FEW/NONE/ENOUGH или пустая строка для null): ");
        String transportStr = scanner.nextLine();
        if (!transportStr.isEmpty()) {
            flat.setTransport(Transport.valueOf(transportStr.toUpperCase()));
        }
        
        // Создание дома
        System.out.print("Введите название дома: ");
        String houseName = scanner.nextLine();
        System.out.print("Введите год постройки (> 0): ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите количество этажей (> 0): ");
        Integer floors = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите количество квартир на этаже (> 0): ");
        int flatsOnFloor = Integer.parseInt(scanner.nextLine());
        
        flat.setHouse(new House(houseName, year, floors, flatsOnFloor));
        
        return flat;
    }
    
    @Override
    public String getName() {
        return "add_if_max";
    }
    
    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента";
    }
} 