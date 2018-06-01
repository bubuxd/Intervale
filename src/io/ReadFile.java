package io;

import person.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    private File file;

    public ReadFile(String path) {
        this.file = new File(path);
    }

    public List<Person> read() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
            throw new FileNotFoundException();
        }

        System.out.println(file.toString());
        List<Person> personList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            do {
                Person p = (Person) ois.readObject();
                personList.add(p);
            } while (ois.available() > 0);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return personList;
    }
}
