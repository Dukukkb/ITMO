package entity;

public class House {
    private String name; //Поле не может быть null
    private int year; //Значение поля должно быть больше 0
    private Integer numberOfFloors; //Значение поля должно быть больше 0
    private int numberOfFlatsOnFloor; //Значение поля должно быть больше 0

    /**
     * Конструктор с параметрами
     * @param name название дома
     * @param year год постройки
     * @param numberOfFloors количество этажей
     * @param numberOfFlatsOnFloor количество квартир на этаже
     */
    public House(String name, int year, Integer numberOfFloors, int numberOfFlatsOnFloor) {
        setName(name);
        setYear(year);
        setNumberOfFloors(numberOfFloors);
        setNumberOfFlatsOnFloor(numberOfFlatsOnFloor);
    }

    /**
     * Конструктор копирования
     * @param other копируемый объект
     */
    public House(House other) {
        this.name = other.name;
        this.year = other.year;
        this.numberOfFloors = other.numberOfFloors;
        this.numberOfFlatsOnFloor = other.numberOfFlatsOnFloor;
    }
    
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Поле не может быть null");
        }
        this.name = name;
    }

    public void setYear(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("Значние поля должно быть больше 0");
        }
        this.year = year;
    }

    public void setNumberOfFloors(Integer numberOfFloors) {
        if (numberOfFloors <= 0) {
            throw new IllegalArgumentException("Значение поля должно быть больше 0");
        }
        this.numberOfFloors = numberOfFloors;
    }

    public void setNumberOfFlatsOnFloor(int numberOfFlatsOnFloor) {
        if (numberOfFlatsOnFloor <= 0) {
            throw new IllegalArgumentException("Значние поля должно быть больше 0");
        }
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    public String getName() {return name;}
    public int getYear() {return year;}
    public Integer getNumberOfFloors() {return numberOfFloors;}
    public int getNumberOfFlatsOnFloor() {return numberOfFlatsOnFloor;}

    @Override
    public String toString() {
        return String.format("%s (Год: %d, Этажей: %d, Квартир на этаже: %d)",
            name, year, numberOfFloors, numberOfFlatsOnFloor);
    }
}
