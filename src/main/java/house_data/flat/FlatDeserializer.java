package house_data.flat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import house_data.person.Person;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlatDeserializer extends StdDeserializer<Flat> {

    protected FlatDeserializer() {
        super(Flat.class);
    }

    @Override
    public Flat deserialize(@NotNull JsonParser jP, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        int number = 0, area = 0;
        List<Person> owners = new ArrayList<>();
        if (jP.nextToken() != JsonToken.START_OBJECT)
            throw new IllegalArgumentException("Токен { не найден!");

        while (jP.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jP.getCurrentName();
            if ("number".equals(fieldName)) {
                jP.nextToken();
                number = jP.getIntValue();
            } else if ("area".equals(fieldName)) {
                jP.nextToken();
                area = jP.getIntValue();
            } else if ("owners".equals(fieldName)) {
                if (jP.nextToken() != JsonToken.START_ARRAY)
                    throw new IllegalArgumentException("Токен [ не найден!");
                ObjectMapper mapper = new ObjectMapper();
                while (jP.nextToken() != JsonToken.END_ARRAY)
                    owners.add(mapper.readValue(jP.getText(), Person.class));
            } else throw new IllegalArgumentException("Некорректный формат Json");
        }
        return new Flat(number, area, owners);
    }
}