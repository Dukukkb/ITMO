package entity;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class JsonManager {
    private final Gson gson;

    public JsonManager() {
        // Создаем Gson с пользовательским адаптером для ZonedDateTime
        this.gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
            .create();
    }

    public FlatCollection loadFromFile(String fileName) throws IOException {
        try (FileReader reader = new FileReader(fileName)) {
            FlatCollection collection = new FlatCollection();
            LinkedList<Flat> flats = gson.fromJson(reader, new com.google.gson.reflect.TypeToken<LinkedList<Flat>>(){}.getType());
            if (flats != null) {
                for (Flat flat : flats) {
                    collection.addFlat(flat);
                }
            }
            collection.updateNextId();
            return collection;
        }
    }

    public void saveToFile(FlatCollection collection, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(collection.getFlats(), writer);
        }
    }

    // Адаптер для сериализации и десериализации ZonedDateTime
    private static class ZonedDateTimeAdapter extends TypeAdapter<ZonedDateTime> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

        @Override
        public void write(JsonWriter out, ZonedDateTime value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(formatter.format(value));
            }
        }

        @Override
        public ZonedDateTime read(JsonReader in) throws IOException {
            String dateString = in.nextString();
            if (dateString == null || dateString.isEmpty()) {
                return null;
            }
            return ZonedDateTime.parse(dateString, formatter);
        }
    }
}
