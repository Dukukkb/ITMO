package entity;

import java.util.Comparator;
import java.util.Objects;

public class Flat implements Comparable<Flat> {
    // Статические компараторы для различных полей
    @SuppressWarnings("NullAway")
    public static final Comparator<Flat> AREA_COMPARATOR = Comparator.comparingInt(Flat::getArea);
    @SuppressWarnings("NullAway")
    public static final Comparator<Flat> ROOMS_COMPARATOR = Comparator.nullsLast(Comparator.comparing(Flat::getNumberOfRooms));
    @SuppressWarnings("NullAway")
    public static final Comparator<Flat> NAME_COMPARATOR = Comparator.nullsLast(Comparator.comparing(Flat::getName));
    @SuppressWarnings("NullAway")
    public static final Comparator<Flat> ID_COMPARATOR = Comparator.comparingInt(Flat::getId);
    
    // Метод для создания составного компаратора
    @SafeVarargs
    public static Comparator<Flat> chainComparators(Comparator<Flat>... comparators) {
        Comparator<Flat> result = comparators[0];
        for (int i = 1; i < comparators.length; i++) {
            result = result.thenComparing(comparators[i]);
        }
        return result;
    }
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int area; //Максимальное значение поля: 597, Значение поля должно быть больше 0
    private Long numberOfRooms; //Поле не может быть null, Значение поля должно быть больше 0
    private Furnish furnish; //Поле может быть null
    private View view; //Поле может быть null
    private Transport transport; //Поле может быть null
    private House house; //Поле не может быть null

    private static int nextId = 1;

    /**
     * Устанавливает следующий доступный id.
     * Используется после загрузки объектов из файла.
     * @param id новое значение для nextId
     */
    public static void setNextId(int id) {
        if (id > nextId) {
            nextId = id;
        }
    }
    
    /**
     * Возвращает текущее значение nextId.
     * @return текущее значение nextId
     */
    public static int getNextId() {
        return nextId;
    }

    /**
     * Конструктор по умолчанию
     * Создает пустой объект с автоматически сгенерированными id и creationDate
     */
    public Flat() {
        this.id = nextId++;
        this.creationDate = java.time.ZonedDateTime.now();
    }
    /**
     * Конструктор с параметрами
     * @param name название квартиры
     * @param coordinates координаты
     * @param area площадь
     * @param numberOfRooms количество комнат
     * @param furnish мебель
     * @param view вид
     * @param transport транспорт
     * @param house дом
     */
    public Flat(String name, Coordinates coordinates, int area, Long numberOfRooms,
                Furnish furnish, View view, Transport transport, House house) {
        this();
        setName(name);
        setCoordinates(coordinates);
        setArea(area);
        setNumberOfRooms(numberOfRooms);
        this.furnish = furnish;
        this.view = view;
        this.transport = transport;
        setHouse(house);
    }

    /**
     * Конструктор копирования
     * @param other копируемый объект
     */
    public Flat(Flat other) {
        this.id = nextId++;
        this.name = other.name;
        this.coordinates = new Coordinates(other.coordinates);
        this.creationDate = java.time.ZonedDateTime.now();
        this.area = other.area;
        this.numberOfRooms = other.numberOfRooms;
        this.furnish = other.furnish;
        this.view = other.view;
        this.transport = other.transport;
        this.house = new House(other.house);
    }

    /**
     * Устанавливает id объекта.
     * Если установленный id больше или равен текущему nextId,
     * то nextId обновляется, чтобы избежать дублирования id в будущем.
     * @param id значение id для установки
     * @throws IllegalArgumentException если id <= 0
     */
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Значение поля должно быть больше 0");
        }
        this.id = id;
        
        if (id >= nextId) {
            nextId = id + 1;
        }
    }

    public void setName(String name) { 
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Поле не может быть null или пустым");
        }
        this.name = name.trim();
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Поле не может быть null");
        }
        this.coordinates = coordinates;
    }

    public void setArea(int area) {
        if (area > 597) {
            throw new IllegalArgumentException("Максимальное значение поля 597");
        }
        if (area <= 0) {
            throw new IllegalArgumentException("Значение поля должно быть больше 0");
        }
        this.area = area;
    }

    public void setNumberOfRooms(Long numberOfRooms) {
        if (numberOfRooms <= 0 || numberOfRooms == null) {
            throw new IllegalArgumentException("Значение поля должно быть больше 0 и не должно быть null");
        }
        this.numberOfRooms = numberOfRooms;
    }

    public void setHouse(House house) {
        if (house == null) {
            throw new IllegalArgumentException("Значение поля не может быть null");
        }
        this.house = house;
    }

    public void setFurnish(Furnish furnish) {
        this.furnish = furnish;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    /**
     * Устанавливает дату создания объекта.
     * После первой установки дата не может быть изменена.
     * @param creationDate дата создания для установки
     */
    public void setCreationDate(java.time.ZonedDateTime creationDate) {
        if (this.creationDate == null) {
            this.creationDate = (creationDate == null) ? 
                java.time.ZonedDateTime.now() : creationDate;
        } else {
            throw new UnsupportedOperationException("creationDate уже установлен и не может быть изменен");
        }
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public Coordinates getCoordinates() {return coordinates;}
    public int getArea() {return area;}
    public Long getNumberOfRooms() {return numberOfRooms;}
    public Furnish getFurnish() {return furnish;}
    public View getView() {return view;}
    public Transport getTransport() {return transport;}
    public House getHouse() {return house;}
    public java.time.ZonedDateTime getCreationDate() {return creationDate;}
    
    @Override
    public String toString() {
        return String.format("Квартира #%d:\n" +
                "  Название: %s\n" +
                "  Координаты: %s\n" +
                "  Дата создания: %s\n" +
                "  Площадь: %d м²\n" +
                "  Количество комнат: %d\n" +
                "  Мебель: %s\n" +
                "  Вид: %s\n" +
                "  Транспорт: %s\n" +
                "  Дом: %s",
                id, name, coordinates,
                creationDate.format(java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")),
                area, numberOfRooms,
                furnish != null ? furnish : "не указано",
                view != null ? view : "не указано",
                transport != null ? transport : "не указано",
                house);
    }

    @Override
    public int compareTo(Flat other) {
        // По умолчанию сортируем по ID
        return Integer.compare(this.id, other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flat flat = (Flat) o;
        return id == flat.id &&
                area == flat.area &&
                numberOfRooms == flat.numberOfRooms &&
                Objects.equals(name, flat.name) &&
                Objects.equals(coordinates, flat.coordinates) &&
                Objects.equals(creationDate, flat.creationDate) &&
                furnish == flat.furnish &&
                view == flat.view &&
                transport == flat.transport &&
                Objects.equals(house, flat.house);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate,
                            area, numberOfRooms, furnish, view, 
                            transport, house);
    }
}