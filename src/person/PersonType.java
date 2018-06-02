package person;

import exception.IncorrectType;

public enum PersonType {
    Worker('w') {
        @Override
        public String print() {
            return "Работник";
        }
    },
    Manager('m') {
        @Override
        public String print() {
            return "Менеджер";
        }
    },
    Other('o') {
        @Override
        public String print() {
            return "Специалист";
        }
    };
    private char shortName;

   PersonType(char s) {
        this.shortName = s;
    }

    public char getShortName() {
        return shortName;
    }

    public static PersonType getType(String personType) throws IncorrectType {
        switch (personType) {
            case "m":
            case "Manager":
                return PersonType.Manager;
            case "o":
            case "Other":
                return PersonType.Other;
            case "w":
            case "Worker":
                return PersonType.Worker;
        }
        throw new IncorrectType();
    }

    public abstract String print();
}
