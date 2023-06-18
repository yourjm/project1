package com.zor07.model;

public class Command {

    private final CommandType type;
    private final Integer id;
    private final Person person;

    public Command(CommandType type, int id, Person person) {
        this.type = type;
        this.id = id;
        this.person = person;
    }

    public Command(CommandType type, int id) {
        this.type = type;
        this.id = id;
        this.person = null;
    }

    public Command(CommandType type, Person person) {
        this.type = type;
        this.person = person;
        this.id = null;
    }

    public Command(CommandType type) {
        this.type = type;
        this.person = null;
        this.id = null;
    }

    public CommandType getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "Command{" +
                "type=" + type +
                ", id=" + id +
                ", value='" + person + '\'' +
                '}';
    }
}
