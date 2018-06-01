package controller;

import exception.*;
import io.ReadFile;
import io.WriteFile;
import person.Manager;
import person.Other;
import person.Person;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class PersonController {
    private List<Person> personList;
    private static String fileInCurentFolder = "file.txt";

    public PersonController() {
        personList = new ArrayList<>();
    }

    public void sort(String arg) throws IncorrectArgument, SortingIsNotPossible {
        if (personList.size() < 2)
            throw new SortingIsNotPossible();
        Comparator<Person> c = personList.get(0).getComparator(arg);
        Collections.sort(personList, c);
    }

    public StringBuilder showAllWorker() throws PersonsListEmpty {
        if (personList.size() < 1)
            throw new PersonsListEmpty();

        StringBuilder rez = new StringBuilder();
        for (Person person : personList) {
            rez.append(person.toString());
            rez.append(System.lineSeparator());
        }
        return rez;
    }

    public int[] createPerson(String firstName, String lastName, String midName, LocalDate birthDate, LocalDate startWorkDate, String personType) throws IncorrectArgument, IncorrectType {
        Person p = Person.createPerson(firstName, lastName, midName, birthDate, startWorkDate, personType);
        personList.add(p);
        return new int[]{p.getId(), personList.size()};
    }

    public int removePerson(String idPerson) throws NotFoundPersonById, IncorrectArgument {
        Person p = getPersonById(idPerson);
        personList.remove(p);
        return personList.size();
    }

    public Person changeType(String idPerson, String newType) throws NotFoundPersonById, IncorrectType, IncorrectArgument {
        Person p = getPersonById(idPerson);
        p.changePersonType(newType);
        return p;
    }

    public Manager setWorker(String idManager, String idWorker) throws NotFoundPersonById, IncorrectArgument {
        Manager m = (Manager) getPersonById(idManager);
        Person p = getPersonById(idWorker);
        m.setWorker(p.getId());
        return m;
    }

    private Person getPersonById(String parseId) throws NotFoundPersonById, IncorrectArgument {
        int id = -1;
        try {
            id = Integer.parseInt(parseId);
        } catch (Exception e) {
            throw new IncorrectArgument();
        }
        for (Person person : personList) {
            if (person.getId() == id) {
                return person;
            }
        }
        throw new NotFoundPersonById(id);
    }


    public void saveWorkerListInCurrentFolder() throws IOException {
        WriteFile file = new WriteFile(new File(PersonController.fileInCurentFolder).getAbsolutePath(), personList);
        file.save();
    }

    public void saveWorkerListInCustomPath(String path) throws IOException {
        WriteFile file = new WriteFile(path, personList);
        file.save();
    }

    public int loadCustomPath(String path) throws IOException {
        ReadFile file = new ReadFile(path);
        personList = file.read();
        return personList.size();
    }

    public int loadCurrentFolder() throws IOException {
        ReadFile file = new ReadFile(new File(PersonController.fileInCurentFolder).getAbsolutePath());
        personList = file.read();
        return personList.size();
    }

    public Other setDescription(String idWorker, String arg) throws NotFoundPersonById, IncorrectArgument {
        Other p = (Other) getPersonById(idWorker);
        p.setDescription(arg);
        return p;
    }


    public String showWorkerInformation(String arg) throws IncorrectArgument, NotFoundPersonById {
        int id;
        try {
            id = Integer.parseInt(arg);
        } catch (Exception e) {
            throw new IncorrectArgument();
        }
        for (Person person : personList) {
            if (person.getId() == id)
                return person.toString();
        }
        throw new NotFoundPersonById(id);
    }

    public String showAllWorkersListInManager(String arg) throws IncorrectArgument, NotFoundPersonById, WorkersListEmpty {
        Manager m = (Manager) getPersonById(arg);
        return String.format("id : %s %s %s %s\n", m.getId(), m.getLastName(), m.getFirstName(), m.printWorkers(personList));
    }

    public List<Person> getWorkersList() {
        return personList;
    }
}
