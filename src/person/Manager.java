package person;

import exception.IncorrectArgument;
import exception.IncorrectType;
import exception.WorkersListEmpty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Person implements Serializable {

    private List<Integer> worker;

    public Manager(String firstName, String lastName, String midName, LocalDate birthDate, LocalDate startWorkDate, String personType) throws IncorrectType {
        super(firstName, lastName, midName, birthDate, startWorkDate, personType);
        worker = new ArrayList<>();
    }

    public void setWorker(int idPerson) throws IncorrectArgument {
        if(idPerson == this.getId())
            throw new IncorrectArgument();
        worker.add(idPerson);
    }

    public void removeWorker(int idPerson){
        int index = -1;
        for (Integer i : worker) {
            if(i == idPerson)
               index = idPerson;
        }
        worker.remove(index);
    }

    public String printWorkers(List<Person> personList) throws WorkersListEmpty {
        if(worker.size() <1)
            throw new WorkersListEmpty();
        StringBuilder rez = new StringBuilder();
        for (int id : worker) {
            for (Person p : personList) {
                if(p.getId() == id)
                    rez.append(String.format("%s : %s %s", p.getId(), p.getLastName(), p.getFirstName()));

            }
        }
        return rez.toString();
    }

    public int workerSize(){
return worker.size();
    }


}
