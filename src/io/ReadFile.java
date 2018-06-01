package io;

import person.*;

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
            while (ois.available() > 0) {
                char shortName = ois.readChar();
                switch (shortName) {
                    case 'w':
                        Worker w = (Worker) ois.readObject();
                        personList.add(w);
                        break;
                    case 'm':
                        Manager m = (Manager) ois.readObject();
                        personList.add(m);
                        break;
                    case 'o':
                        Other o = (Other) ois.readObject();
                        personList.add(o);
                        break;
                    default:
                        System.out.println("Произошла ошибка при загрузке :" + shortName);
                }
            }
            System.out.println("pS " +personList.size());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return personList;
    }
}
