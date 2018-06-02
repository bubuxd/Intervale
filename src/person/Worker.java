package person;

import exception.IncorrectType;

import java.io.Serializable;
import java.time.LocalDate;

public class Worker extends Person implements Serializable {
    public Worker(String firstName, String lastName, String midName, LocalDate birthDate, LocalDate startWorkDate, String personType) throws IncorrectType {
        super(firstName, lastName, midName, birthDate, startWorkDate, personType);
    }

    public Worker(String[] person) throws IncorrectType {
        super(person[0],person[1], person[2], person[3],
                LocalDate.parse(person[4], formatter),LocalDate.parse(person[5], formatter),
                person[6]);
    }

    @Override
    public String save() {
        return String.format("%s %s %s %s %s %s %s",this.getId(),
                this.getFirstName(), this.getLastName(), this.getMidName(),
                this.getBirthDate().format(formatter), this.getStartWorkDate().format(formatter),
                this.getPersonType());
    }

    @Override
    public String toString() {
        return String.format("%s : %s %s %s %s %s %s", getId(), getLastName(), getFirstName(),
                getMidName(), getPersonType().print(), getBirthDate().format(formatter),
                getStartWorkDate().format(formatter));
    }
}
