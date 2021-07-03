package house_data.flat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import house_data.person.Person;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class FlatSerializer extends StdSerializer<Flat> {

    protected FlatSerializer() {
        super(Flat.class);
    }

    @Override
    public void serialize(@NotNull Flat flat, @NotNull JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject();
        jGen.writeNumberField("number", flat.getNumber());
        jGen.writeNumberField("area", flat.getNumber());
        jGen.writeFieldName("owners");
        jGen.writeStartArray();
        ObjectMapper mapper = new ObjectMapper();
        for (Person p : flat.getOwners())
            jGen.writeString(mapper.writeValueAsString(p));
        jGen.writeEndArray();
        jGen.writeEndObject();
    }
}