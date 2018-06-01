package person;

import exception.IncorrectType;

import java.io.Serializable;
import java.time.LocalDate;

public class Other extends Person implements Serializable {
    private String description;

    public Other(String firstName, String lastName, String midName, LocalDate birthDate, LocalDate startWorkDate, String personType) throws IncorrectType {
        super(firstName, lastName, midName, birthDate, startWorkDate, personType);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
