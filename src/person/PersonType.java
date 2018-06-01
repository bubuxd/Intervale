package person;

import exception.IncorrectType;

public enum PersonType {
    Worker {
        @Override
        public String print() {
            return "Работник";
        }
    },
    Manager {
        @Override
        public String print() {
            return "Менеджер";
        }
    },
    Other {
        @Override
        public String print() {
            return "Просто спец";
        }
    };

    public static PersonType getType(String personType) throws IncorrectType {
        switch (personType) {
            case "m":
                return PersonType.Manager;
            case "o":
                return PersonType.Other;
            case "w":
                return PersonType.Worker;
        }
        throw new IncorrectType();
    }

    public abstract String print();
}
