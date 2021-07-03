package house_data.flat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import house_data.person.Person;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//@JsonSerialize(using = FlatSerializer.class)
public class Flat implements Serializable {
    private final int number;
    private final double area;
    private final List<Person> owners;

    public Flat() {
        this.number = 0;
        this.area = 0.0;
        this.owners = null;
    }

    public Flat(int number, double area, List<Person> owners) {
        if (number < 0 || area < 0)
            throw new IllegalArgumentException("Некорректные данные квартиры!");
        this.number = number;
        this.area = area;
        this.owners = owners;
    }

    public int getNumber() {
        return number;
    }

    public double getArea() {
        return area;
    }

    public List<Person> getOwners() {
        return owners;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return number == flat.number &&
                Double.compare(flat.area, area) == 0 &&
                Objects.equals(owners, flat.owners);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, area, owners);
    }

    @Override
    public String toString() {
        return "Flat{" +
                "number=" + number +
                ", area=" + area +
                ", owners=" + owners +
                '}';
    }
}