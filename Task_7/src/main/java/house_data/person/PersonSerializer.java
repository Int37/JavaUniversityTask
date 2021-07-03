package house_data.person;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class PersonSerializer extends StdSerializer<Person> {

    protected PersonSerializer() {
        super(Person.class);
    }

    @Override
    public void serialize(@NotNull Person person, @NotNull JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject();
        jGen.writeStringField("fullName", person.getName() + " " + person.getSurname() + " " + person.getMiddleName());
        jGen.writeNumberField("dob", person.getDOB().getTime());
        jGen.writeEndObject();
    }
}