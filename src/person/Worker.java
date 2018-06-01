package person;

import exception.IncorrectType;

import java.io.Serializable;
import java.time.LocalDate;

public class Worker extends Person implements Serializable {
    public Worker(String firstName, String lastName, String midName, LocalDate birthDate, LocalDate startWorkDate, String personType) throws IncorrectType {
        super(firstName, lastName, midName, birthDate, startWorkDate, personType);
    }
}
