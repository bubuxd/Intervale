package person;

import exception.IncorrectArgument;
import exception.IncorrectType;
import exception.WorkersListEmpty;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Person implements Serializable {

    private List<Integer> worker;

    public Manager(String firstName, String lastName, String midName, LocalDate birthDate, LocalDate startWorkDate, String personType) throws IncorrectType {
        super(firstName, lastName, midName, birthDate, startWorkDate, personType);
        worker = new ArrayList<>();
    }

    public Manager(String[] person, String workerList) throws IncorrectArgument, IncorrectType {
        super(person[0], person[1], person[2], person[3],
                LocalDate.parse(person[4], formatter), LocalDate.parse(person[5], formatter),
                person[6]);
        worker = new ArrayList<>();
        if (workerList.length() > 0)
            for (String idWorker : workerList.trim().split(" ")) {
                this.setWorker(Integer.parseInt(idWorker));
            }
    }


    public void setWorker(int idPerson){
        if (idPerson != this.getId())
            worker.add(idPerson);
    }

    public int removeWorker(int idPerson) {
        int index = -1;
        for (Integer i : worker) {
            if (i == idPerson)
                index = idPerson;
        }
        worker.remove(index);
        return workerSize();
    }

    public String printWorkers(List<Person> personList) throws WorkersListEmpty {
        if (worker.size() < 1)
            throw new WorkersListEmpty();
        StringBuilder rez = new StringBuilder();
        System.out.println(personList.size());
        System.out.println("\nРаботники :\n");
        for (int id : worker) {
            for (Person p : personList) {
                if (p.getId() == id)
                    rez.append(String.format("%s : %s %s", p.getId(), p.getLastName(), p.getFirstName()));

            }
        }
        return rez.toString();
    }

    public int workerSize() {
        return worker.size();
    }

    @Override
    public String save() {
        String idWorker = "";
        for (Integer i : worker) {
            idWorker += i + " ";
        }
        idWorker = idWorker.trim();
        return String.format("%s %s %s %s %s %s %s%s%s", this.getId(), this.getFirstName(), this.getLastName(), this.getMidName(),
                this.getBirthDate().format(formatter), this.getStartWorkDate().format(formatter),
                this.getPersonType(), Person.customArgumentSeparator, idWorker);
    }

    @Override
    public String toString() {
        String dopArgument = "";
        dopArgument += "Работников в подчинении :";
        dopArgument += workerSize();
        return String.format("%s : %s %s %s %s %s %s %s", getId(), getLastName(), getFirstName(),
                getMidName(), getPersonType().print(), getBirthDate().format(formatter),
                getStartWorkDate().format(formatter), dopArgument);
    }

}
