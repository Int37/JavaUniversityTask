import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Objects;

public class Human {
    protected String name;
    protected String surname;
    protected String middleName;
    protected Calendar dateOfBirth;

    public Human(String name, String surname, String middleName, Calendar dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
    }

    @Contract(pure = true)
    public Human(@NotNull Human per) {
        this.name = per.name;
        this.surname = per.surname;
        this.middleName = per.middleName;
        this.dateOfBirth = per.dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(name, human.name) &&
                Objects.equals(surname, human.surname) &&
                Objects.equals(middleName, human.middleName) &&
                Objects.equals(dateOfBirth, human.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, middleName, dateOfBirth);
    }

    @Override
    public String toString() {
        return "collections.Human{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}