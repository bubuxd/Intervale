package io;

import exception.IncorrectArgument;
import exception.IncorrectType;
import exception.ParseFileException;
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
        personList = customReeader();
        return personList;
    }

    private List<Person> customReeader() {
        List<Person> personList = new ArrayList<>();
        System.out.println("Файл прочитан по адресу : " + file.toString());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                Person p = separationPersonAndParametr(reader.readLine());
                personList.add(p);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseFileException e) {
            e.printStackTrace();
        } catch (IncorrectType incorrectType) {
            incorrectType.printStackTrace();
        } catch (IncorrectArgument incorrectArgument) {
            incorrectArgument.printStackTrace();
        }
        return personList;
    }

    private Person separationPersonAndParametr(String s) throws ParseFileException, IncorrectType, IncorrectArgument {
        String[] personAndParam = s.split(Person.customArgumentSeparator);
        String[] person = personAndParam[0].split(" ");
        switch (person[person.length - 1]) {
            case "Worker":
                return new Worker(person);
            case "Manager":
                if (personAndParam.length > 2)
                    return new Manager(person, personAndParam[1]);
                else
                    return new Manager(person, "");
            case "Other":
                if (personAndParam.length > 2)
                    return new Other(person, personAndParam[1]);
                else
                    return new Other(person, "");
        }
        throw new ParseFileException();
    }
}
