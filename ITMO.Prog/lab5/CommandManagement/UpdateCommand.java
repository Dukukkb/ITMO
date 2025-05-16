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
 * Команда обновления элемента коллекции по id
 */
public class UpdateCommand implements Command {
    private final FlatCollection collection;
    
    /**
     * Конструктор команды update_id
     * @param collection коллекция для обновления элемента
     */
    public UpdateCommand(FlatCollection collection) {
        this.collection = collection;
    }
    
    @Override
    public String execute(String[] args) {
        if (args.length < 1) {
            return "Использование: update_id <id>";
        }
        
        try {
            int id = Integer.parseInt(args[0]);
            Flat existingFlat = collection.findById(id);
            
            if (existingFlat == null) {
                return "Квартира с id=" + id + " не найдена";
            }
            
            updateFlatFromUserInput(existingFlat);
            return "Квартира с id=" + id + " успешно обновлена";
        } catch (NumberFormatException e) {
            return "Ошибка: id должен быть целым числом";
        } catch (Exception e) {
            return "Ошибка при обновлении квартиры: " + e.getMessage();
        }
    }
    
    /**
     * Обновляет поля объекта Flat из пользовательского ввода
     * @param flat объект Flat для обновления
     */
    private void updateFlatFromUserInput(Flat flat) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Введите новое название квартиры (или Enter для сохранения текущего): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            flat.setName(name);
        }
        
        System.out.print("Обновить координаты? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Введите координату X (> -262): ");
            Float x = Float.parseFloat(scanner.nextLine());
            System.out.print("Введите координату Y: ");
            Double y = Double.parseDouble(scanner.nextLine());
            flat.setCoordinates(new Coordinates(x, y));
        }
        
        System.out.print("Введите новую площадь (> 0, <= 597) или Enter для сохранения текущей: ");
        String areaStr = scanner.nextLine();
        if (!areaStr.isEmpty()) {
            flat.setArea(Integer.parseInt(areaStr));
        }
        
        System.out.print("Введите новое количество комнат (> 0) или Enter для сохранения текущего: ");
        String roomsStr = scanner.nextLine();
        if (!roomsStr.isEmpty()) {
            flat.setNumberOfRooms(Long.parseLong(roomsStr));
        }
        
        System.out.print("Изменить мебель? (DESIGNER/NONE/LITTLE или пустая строка для null): ");
        String furnishStr = scanner.nextLine();
        if (!furnishStr.isEmpty()) {
            if (furnishStr.equalsIgnoreCase("null")) {
                flat.setFurnish(null);
            } else {
                flat.setFurnish(Furnish.valueOf(furnishStr.toUpperCase()));
            }
        }
        
        System.out.print("Изменить вид? (PARK/BAD/NORMAL/GOOD/TERRIBLE или пустая строка для null): ");
        String viewStr = scanner.nextLine();
        if (!viewStr.isEmpty()) {
            if (viewStr.equalsIgnoreCase("null")) {
                flat.setView(null);
            } else {
                flat.setView(View.valueOf(viewStr.toUpperCase()));
            }
        }
        
        System.out.print("Изменить транспорт? (FEW/NONE/ENOUGH или пустая строка для null): ");
        String transportStr = scanner.nextLine();
        if (!transportStr.isEmpty()) {
            if (transportStr.equalsIgnoreCase("null")) {
                flat.setTransport(null);
            } else {
                flat.setTransport(Transport.valueOf(transportStr.toUpperCase()));
            }
        }
        
        System.out.print("Обновить информацию о доме? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Введите название дома: ");
            String houseName = scanner.nextLine();
            System.out.print("Введите год постройки (> 0): ");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.print("Введите количество этажей (> 0): ");
            Integer floors = Integer.parseInt(scanner.nextLine());
            System.out.print("Введите количество квартир на этаже (> 0): ");
            int flatsOnFloor = Integer.parseInt(scanner.nextLine());
            
            flat.setHouse(new House(houseName, year, floors, flatsOnFloor));
        }
    }
    
    @Override
    public String getName() {
        return "update_id";
    }
    
    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции по его id";
    }
} 