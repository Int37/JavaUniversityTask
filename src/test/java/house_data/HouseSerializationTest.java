package house_data;

import house_data.flat.Flat;
import house_data.house.House;
import house_data.person.Person;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import InputOutput.InputOutput;

public class HouseSerializationTest {
    @Test
    public void serializeDeserializeHouseTest() throws IOException, ClassNotFoundException {
        Person p1 = new Person("Иванов", "Иван", "Иванович", new Date(1983, Calendar.MAY, 4));
        Person p2 = new Person("Иванов", "Степан", "Иванович", new Date(1973, Calendar.MAY, 4));
        Person p3 = new Person("Петров", "Василий", "Иванович", new Date(1989, Calendar.MAY, 4));
        Person p4 = new Person("Петрова", "Людмила", "Ивановна", new Date(1992, Calendar.MAY, 4));
        Person p5 = new Person("Семенов", "Игорь", "Иванович", new Date(1978, Calendar.MAY, 4));

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);

        ArrayList<Person> personList1 = new ArrayList<>();
        personList1.add(p3);
        personList1.add(p4);

        ArrayList<Person> personList2 = new ArrayList<>();
        personList2.add(p5);

        ArrayList<Flat> fList = new ArrayList<>();
        Flat f1 = new Flat(1, 50.9, personList);
        Flat f2 = new Flat(2, 60.9, personList1);
        Flat f3 = new Flat(3, 40.9, personList2);
        fList.add(f1);
        fList.add(f2);
        fList.add(f3);

        House h = new House("123332212", "ul.Pushkina, 29",
                new Person("Иванов", "Иван", "Иванович", new Date(1983, Calendar.MAY, 4)), fList);

        HouseSerialization.serializeHouse(h, "Test.txt");
        House h1 = HouseSerialization.deserializeHouse("Test.txt");
        assertEquals(h, h1);
    }

    @Test
    public void serializeDeserializeHouseJSONTest() throws IOException, ClassNotFoundException {
        Person p1 = new Person("Иванов", "Иван", "Иванович", new Date(1983, Calendar.MAY, 4));
        Person p2 = new Person("Иванов", "Степан", "Иванович", new Date(1973, Calendar.MAY, 4));
        Person p3 = new Person("Петров", "Василий", "Иванович", new Date(1989, Calendar.MAY, 4));
        Person p4 = new Person("Петрова", "Людмила", "Ивановна", new Date(1992, Calendar.MAY, 4));
        Person p5 = new Person("Семенов", "Игорь", "Иванович", new Date(1978, Calendar.MAY, 4));

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);

        ArrayList<Person> personList1 = new ArrayList<>();
        personList1.add(p3);
        personList1.add(p4);

        ArrayList<Person> personList2 = new ArrayList<>();
        personList2.add(p5);

        ArrayList<Flat> fList = new ArrayList<>();
        Flat f1 = new Flat(1, 50.9, personList);
        Flat f2 = new Flat(2, 60.9, personList1);
        Flat f3 = new Flat(3, 40.9, personList2);
        fList.add(f1);
        fList.add(f2);
        fList.add(f3);

        House h = new House("123332212", "ul.Pushkina, 29",
                new Person("Иванов", "Иван", "Иванович", new Date(1983, Calendar.MAY, 4)), fList);

        String out = HouseSerialization.serializeHouseJSON(h);
        House h1 = HouseSerialization.deserializeHouseJSON(out);
        String out2 = HouseSerialization.serializeHouseJSON(h);
        assertTrue(HouseSerialization.equalsJSON(out,out2));

        assertEquals(h, h1);

    }

    @Test
    public void toCsvTest() throws IOException {
        List<File> ways=InputOutput.filesWithExtension(".","csv");
        for (File file:
             ways) {
            file.delete();

        }
        Person p1 = new Person("Иванов", "Иван", "Иванович", new Date(1983, Calendar.MAY, 4));
        Person p2 = new Person("Иванов", "Степан", "Иванович", new Date(1973, Calendar.MAY, 4));
        Person p3 = new Person("Петров", "Василий", "Иванович", new Date(1989, Calendar.MAY, 4));
        Person p4 = new Person("Петрова", "Людмила", "Ивановна", new Date(1992, Calendar.MAY, 4));
        Person p5 = new Person("Семенов", "Игорь", "Иванович", new Date(1978, Calendar.MAY, 4));

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);

        ArrayList<Person> personList1 = new ArrayList<>();
        personList1.add(p3);
        personList1.add(p4);

        ArrayList<Person> personList2 = new ArrayList<>();
        personList2.add(p5);

        ArrayList<Flat> fList = new ArrayList<>();
        Flat f1 = new Flat(1, 50.9, personList);
        Flat f2 = new Flat(2, 60.9, personList1);
        Flat f3 = new Flat(3, 40.9, personList2);
        fList.add(f1);
        fList.add(f2);
        fList.add(f3);

        House h = new House("123332212", "ul.Pushkina, 29",
                new Person("Иванов", "Иван", "Иванович", new Date(1983, Calendar.MAY, 4)), fList);

        HouseSerialization.houseToCSV(h,".");
         String except = ";Данные о домеКадастровый номер:;;123332212Адрес:;;ul.Pushkina, 29Старший по дому:;;Иван Иванов Иванович;Данные о квартирах№;Площадь, кв. м;Владельцы1;50.9;Иван И.И., Степан И.И.2;60.9;Василий П.И., Людмила П.И.3;40.9;Игорь С.И.";
        List<File> way=InputOutput.filesWithExtension(".","csv");
        String content = Files.lines(Paths.get(String.valueOf(way.get(0)))).reduce("", String::concat);
        System.out.println(content);
        assertEquals(except,content);




    }}
