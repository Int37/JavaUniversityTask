package house_data.house;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import house_data.flat.Flat;
import house_data.house.House;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HouseSerializer extends StdSerializer<House> {

    protected HouseSerializer() {
        super(House.class);
    }

    @Override
    public void serialize(@NotNull House house, @NotNull JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject();
        jGen.writeStringField("cadastralNum", house.getCadastralNum());
        jGen.writeStringField("address", house.getAddress());
        ObjectMapper mapper = new ObjectMapper();
        jGen.writeStringField("housewife", mapper.writeValueAsString(house.getHousewife()));

        jGen.writeFieldName("apartments");
        jGen.writeStartArray();
        for (Flat f : house.getApartments())
            jGen.writeString(mapper.writeValueAsString(f));
        jGen.writeEndArray();
        jGen.writeEndObject();
    }
}