package person;

import exception.IncorrectType;

import java.io.Serializable;
import java.time.LocalDate;

public class Other extends Person implements Serializable {
    private String description;

    public Other(String firstName, String lastName, String midName, LocalDate birthDate, LocalDate startWorkDate, String personType) throws IncorrectType {
        super(firstName, lastName, midName, birthDate, startWorkDate, personType);
    }

    public Other(String[] person, String s) throws IncorrectType {
        super(person[0],person[1], person[2], person[3],
                LocalDate.parse(person[4], formatter),LocalDate.parse(person[5], formatter),
                person[6]);
        setDescription(s);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String save() {
        return String.format("%s %s %s %s %s %s %s%s%s",this.getId(), this.getFirstName(), this.getLastName(), this.getMidName(),
                this.getBirthDate().format(formatter), this.getStartWorkDate().format(formatter),
                this.getPersonType(),Person.customArgumentSeparator,description);
    }
}
