package com.zor07.model;

public class Command {

    private final CommandType type;
    private final Integer id;
    private final String value;

    public Command(CommandType type, int id, String value) {
        this.type = type;
        this.id = id;
        this.value = value;
    }

    public Command(CommandType type, int id) {
        this.type = type;
        this.id = id;
        this.value = null;
    }

    public Command(CommandType type, String value) {
        this.type = type;
        this.value = value;
        this.id = null;
    }

    public Command(CommandType type) {
        this.type = type;
        this.value = null;
        this.id = null;
    }

    public CommandType getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Command{" +
                "type=" + type +
                ", id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
