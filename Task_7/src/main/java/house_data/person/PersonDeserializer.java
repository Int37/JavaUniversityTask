package house_data.person;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import house_data.flat.Flat;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Date;

public class PersonDeserializer extends StdDeserializer<Person> {

    protected PersonDeserializer() {
        super(Person.class);
    }

    @Override
    public Person deserialize(@NotNull JsonParser jP, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String name = "", surname = "", middleName = "";
        Date dob = null;
        if (jP.nextToken() != JsonToken.START_OBJECT)
            throw new IllegalArgumentException("Токен { не найден!");

        while (jP.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jP.getCurrentName();
            if ("fullName".equals(fieldName)) {
                jP.nextToken();
                String[] fio = jP.getText().split(" ");
                if (fio.length != 3)
                    throw new IllegalArgumentException("Некорректное значение fullName");
                name = fio[0];
                surname = fio[1];
                middleName = fio[2];
            } else if ("dob".equals(fieldName)) {
                jP.nextToken();
                dob = new Date(jP.getLongValue());
            } else throw new IllegalArgumentException("Некорректный формат Json");
        }
        return new Person(name, surname, middleName, dob);
    }
}