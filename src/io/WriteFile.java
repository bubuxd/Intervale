package io;

import person.Person;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        System.out.println("Файл сохранен по адресу : "+file.toString());
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8"))) ) {
            for (Person person : personList) {
                writer.write(person.save());
                writer.write(System.lineSeparator());
                writer.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
