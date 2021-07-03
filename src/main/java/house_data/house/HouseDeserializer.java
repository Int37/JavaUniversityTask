package house_data.house;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import house_data.flat.Flat;
import house_data.person.Person;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HouseDeserializer extends StdDeserializer<House> {

    protected HouseDeserializer() {
        super(House.class);
    }

    @Override
    public House deserialize(@NotNull JsonParser jP, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String cadastralNum = "", address = "";
        Person housewife = null;
        List<Flat> apartments = new ArrayList<>();
        if (jP.nextToken() != JsonToken.START_OBJECT)
            throw new IllegalArgumentException("Токен { не найден!");

        while (jP.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jP.getCurrentName();
            if ("cadastralNum".equals(fieldName)) {
                jP.nextToken();
                cadastralNum = jP.getText();
            } else if ("address".equals(fieldName)) {
                jP.nextToken();
                address = jP.getText();
            } else if ("housewife".equals(fieldName)) {
                ObjectMapper mapper = new ObjectMapper();
                housewife = mapper.readValue(jP.getText(), Person.class);
            } else if ("apartments".equals(fieldName)) {
                if (jP.nextToken() != JsonToken.START_ARRAY)
                    throw new IllegalArgumentException("Токен [ не найден!");
                ObjectMapper mapper = new ObjectMapper();
                while (jP.nextToken() != JsonToken.END_ARRAY)
                    apartments.add(mapper.readValue(jP.getText(), Flat.class));
            } else throw new IllegalArgumentException("Некорректный формат Json");
        }
        return new House(cadastralNum, address, housewife, apartments);
    }
}