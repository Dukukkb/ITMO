package entity;

import java.time.ZonedDateTime;
import java.util.LinkedList;

public class FlatCollection {
    private LinkedList<Flat> flats;
    private ZonedDateTime initializationDate;

    /**
     * Конструктор по умолчанию.
     * Создает пустую коллекцию с текущей датой инициализации.
     */
    public FlatCollection() {
        this.flats = new LinkedList<>();
        this.initializationDate = ZonedDateTime.now();
    }
    
    /**
     * Обновляет счетчик nextId в классе Flat на основе максимального id в коллекции.
     * Вызывается после загрузки коллекции из файла.
     */
    public void updateNextId() {
        if (flats.isEmpty()) return;
        
        int maxId = 0;
        for (Flat flat : flats) {
            if (flat.getId() > maxId) {
                maxId = flat.getId();
            }
        }
        Flat.setNextId(maxId + 1);
    }

    /**
     * Добавляет квартиру в коллекцию.
     * @param flat квартира для добавления
     */
    public void addFlat(Flat flat) {
        flats.add(flat);
    }

    /**
     * Удаляет квартиру из коллекции.
     * @param flat квартира для удаления
     */
    public void removeFlat(Flat flat) {
        flats.remove(flat);
    }
    
    /**
     * Находит квартиру по id.
     * @param id идентификатор квартиры
     * @return квартиру с указанным id или null, если квартира не найдена
     */
    public Flat findById(int id) {
        for (Flat flat : flats) {
            if (flat.getId() == id) {
                return flat;
            }
        }
        return null;
    }
    
    /**
     * Удаляет квартиру по id.
     * @param id идентификатор квартиры для удаления
     * @return true если квартира была удалена, false если не найдена
     */
    public boolean removeById(int id) {
        Flat flat = findById(id);
        if (flat != null) {
            flats.remove(flat);
            return true;
        }
        return false;
    }
    
    /**
     * Возвращает список квартир.
     * @return список квартир
     */
    public LinkedList<Flat> getFlats() {
        return flats;
    }
    
    /**
     * Возвращает размер коллекции.
     * @return количество элементов в коллекции
     */
    public int getSize() {
        return flats.size();
    }
    
    /**
     * Очищает коллекцию.
     */
    public void clear() {
        flats.clear();
    }
    
    /**
     * Проверяет, пуста ли коллекция.
     * @return true если коллекция пуста, false иначе
     */
    public boolean isEmpty() {
        return flats.isEmpty();
    }
    
    /**
     * Возвращает дату инициализации коллекции.
     * @return дата инициализации
     */
    public ZonedDateTime getInitializationDate() {
        return initializationDate;
    }
}