package person;

import exception.IncorrectArgument;
import exception.IncorrectType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

public abstract class Person {
    private static int currentId = 0;

    private int id;
    private String lastName;
    private String firstName;
    private String midName;
    private LocalDate birthDate;
    private LocalDate startWorkDate;
    private PersonType personType;

    public Person(String firstName, String lastName, String midName, LocalDate birthDate, LocalDate startWorkDate, String personType) throws IncorrectType {
        this.id = currentId++;
        this.lastName = lastName;
        this.firstName = firstName;
        this.midName = midName;
        this.birthDate = birthDate;
        this.startWorkDate = startWorkDate;
        this.personType = PersonType.getType(personType);
    }

    public static int getCurrentId() {
        return currentId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMidName() {
        return midName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getStartWorkDate() {
        return startWorkDate;
    }

    public String getPersonTypeString() {
        return personType.print();
    }
    public PersonType getPersonType() {
        return personType;
    }

    public int getId() {
        return id;
    }

    public void changePersonType(String personType) throws IncorrectType {
        this.personType = PersonType.getType(personType);
    }

    public static Person createPerson(String firstName, String lastName, String midName, LocalDate birthDate, LocalDate startWorkDate, String personType) throws IncorrectArgument, IncorrectType {
        switch (personType) {
            case "m":
                return new Manager(firstName, lastName, midName, birthDate, startWorkDate, personType);
            case "o":
                return new Other(firstName, lastName, midName, birthDate, startWorkDate, personType);
            case "w":
                return new Worker(firstName, lastName, midName, birthDate, startWorkDate, personType);
        }
        throw new IncorrectArgument();
    }


    public Comparator<Person> getComparator(String s) throws IncorrectArgument {
        switch (s) {
            case "b":
                return new PersonBirthDateComparator();
            case "w":
                return new PersonStartWorkDateComparator();
            case "f":
                return new PersonFirstNameComparator();
            case "l":
                return new PersonLastNameComparator();
            case "m":
                return new PersonMidNameComparator();
        }
        throw new IncorrectArgument();
    }


    class PersonFirstNameComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getFirstName().compareTo(o2.getFirstName());
        }
    }

    class PersonMidNameComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getMidName().compareTo(o2.getMidName());
        }
    }

    class PersonLastNameComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getLastName().compareTo(o2.getLastName());
        }
    }

    class PersonStartWorkDateComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getStartWorkDate().compareTo(o2.getStartWorkDate());
        }
    }

    class PersonBirthDateComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getBirthDate().compareTo(o2.getBirthDate());
        }
    }

    @Override
    public String toString() {
        String workersSize = "";
        if(personType == PersonType.Manager){
            workersSize += "Работников в подчинении :";
            workersSize += ((Manager)this).workerSize();
        }
        return String.format("%s : %s %s %s %s %s %s %s",id, lastName, firstName, midName, personType.print(), birthDate, startWorkDate, workersSize);
    }
}
