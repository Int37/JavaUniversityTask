package house_data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import house_data.house.House;
import house_data.person.Person;
import house_data.flat.Flat;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.List;

public class HouseSerialization {

    public static void serializeHouse(House house, String file) throws IOException {
        try (ObjectOutput out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            out.writeObject(house);
        }
    }

    public static House deserializeHouse(String filename) throws IOException, ClassNotFoundException, ClassCastException {
        try (ObjectInput in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(filename)))) {
            return (House) in.readObject();
        }
    }

    public static void houseToCSV(@NotNull House house, String catalog) throws IOException {
        File ctg = new File(catalog);
        if (!ctg.isDirectory())
            throw new IllegalArgumentException("Требуется каталог для сохранения файла!");
        File csv = new File(ctg.getAbsoluteFile() + "house_" + house.getCadastralNum() + ".csv");
      /*  if (csv.createNewFile()) {
            throw new IllegalArgumentException("Файл этого дома уже существует!");
        }*/
        try (FileWriter fw = new FileWriter(csv)) {
            fw.append(";Данные о доме\n");
            fw.append("Кадастровый номер:;;").append(house.getCadastralNum()).append("\n");
            fw.append("Адрес:;;").append(house.getAddress()).append("\n");
            Person hW = house.getHousewife();
            fw.append("Старший по дому:;;")
                    .append(hW.getSurname()).append(" ")
                    .append(hW.getName()).append(" ")
                    .append(hW.getMiddleName()).append("\n");
            fw.append(";Данные о квартирах\n№;Площадь, кв. м;Владельцы\n");
            for (Flat f : house.getApartments()) {
                fw.append(String.valueOf(f.getNumber())).append(";");
                fw.append(String.valueOf(f.getArea())).append(";");
                List<Person> owns = f.getOwners();
                for (int i = 0; i < owns.size(); i++) {
                    fw.append(owns.get(i).getSurname()).append(" ");
                    fw.append(owns.get(i).getName().charAt(0)).append(".");
                    fw.append(owns.get(i).getMiddleName().charAt(0)).append(".");
                    // Если не последний
                    if (i != owns.size() - 1)
                        fw.append(", ");
                }
                fw.append("\n");
                fw.flush();
            }
        }
    }

    public static String serializeHouseJSON(House house) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(house);
    }

    public static House deserializeHouseJSON(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, House.class);
    }

    public static boolean equalsJSON(String jsonStr1, String jsonStr2) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree1 = mapper.readTree(jsonStr1);
        JsonNode tree2 = mapper.readTree(jsonStr2);
        /*for (Iterator<Map.Entry<String, JsonNode>> it = tree1.fields(); it.hasNext(); ) {
            Map.Entry<String, JsonNode> entry = it.next();
            for (Iterator<Map.Entry<String, JsonNode>> it2 = tree2.fields(); it2.hasNext();){
                Map.Entry<String, JsonNode> entry2 = it2.next();
                if(JsonAssert.assertJsonEquals(entry, entry2))

            }
        }*/
        return tree1.equals(tree2);
    }
}