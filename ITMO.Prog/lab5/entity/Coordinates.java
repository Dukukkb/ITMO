package entity;

public class Coordinates {
    private Float x; //Значение поля должно быть больше -262, Поле не может быть null
    private Double y; //Поле не может быть null

    public Coordinates(Float x, Double y) {
        if(x == null || y == null) {
            throw new IllegalArgumentException("Значение поля не может быть null");
        }
        if (x <= -262) {
            throw new IllegalArgumentException("Значение поля должно быть больше -262");
        }

        this.x = x;
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    /**
     * Конструктор копирования
     * @param other копируемый объект
     */
    public Coordinates(Coordinates other) {
        this.x = other.x;
        this.y = other.y;
    }
}