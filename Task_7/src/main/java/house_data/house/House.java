package house_data.house;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import house_data.person.Person;
import house_data.flat.Flat;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//@JsonSerialize(using = HouseSerializer.class)
public class House implements Serializable {
    private final String cadastralNum;
    private final String address;
    private final Person housewife;
    private final List<Flat> apartments;

    public House() {
        this.cadastralNum = "";
        this.address = "";
        this.housewife = null;
        this.apartments = null;
    }

    public House(String cadastralNum, String address, Person housewife, List<Flat> apartments) {
        this.cadastralNum = cadastralNum;
        this.address = address;
        this.housewife = housewife;
        this.apartments = apartments;
    }

    public String getCadastralNum() {
        return cadastralNum;
    }

    public String getAddress() {
        return address;
    }

    public Person getHousewife() {
        return housewife;
    }

    public List<Flat> getApartments() {
        return apartments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(cadastralNum, house.cadastralNum) &&
                Objects.equals(address, house.address) &&
                Objects.equals(housewife, house.housewife) &&
                Objects.equals(apartments, house.apartments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cadastralNum, address, housewife, apartments);
    }

    @Override
    public String toString() {
        return "House{" +
                "cadastralNum='" + cadastralNum + '\'' +
                ", address='" + address + '\'' +
                ", housewife=" + housewife +
                ", apartments=" + apartments +
                '}';
    }
}