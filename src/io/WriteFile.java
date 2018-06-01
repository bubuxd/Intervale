package io;

import person.Manager;
import person.Person;
import person.PersonType;

import java.io.*;
import java.util.List;

public class WriteFile {
    private File file;
    private List<Person> personList;

    public WriteFile(String path, List<Person> personList) {
        this.file = new File(path);
        this.personList = personList;
    }

    public void save() throws IOException {
        if (!file.exists())
            file.createNewFile();
        System.out.println(file.toString());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Person person : personList) {
//                oos.writeChars(person.getPersonTypeString());
//                if(person.getPersonType() == PersonType.Manager)
                oos.writeObject(person);
                oos.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
