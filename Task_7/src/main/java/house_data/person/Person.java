package house_data.person;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

//@JsonSerialize(using = PersonSerializer.class)
public class Person implements Serializable {
    private final String name;
    private final String surname;
    private final String middleName;
    private final Date dob;

    public Person() {
        this.name = "";
        this.surname = "";
        this.middleName = "";
        this.dob = new Date();
    }

    public Person(@NotNull String name, @NotNull String surname, @NotNull String middleName, Date dob) {
        this.name = name.trim();
        this.surname = surname.trim();
        this.middleName = middleName.trim();
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Date getDOB() {
        return dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(middleName, person.middleName) &&
                Objects.equals(dob, person.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, middleName, dob);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}