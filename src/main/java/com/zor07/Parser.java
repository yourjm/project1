package com.zor07;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zor07.model.Command;
import com.zor07.model.CommandType;
import com.zor07.model.Commands;
import com.zor07.model.Person;

import java.util.Arrays;
import java.util.List;

public class Parser {


    private final Validator validator;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Parser(Validator validator) {
        this.validator = validator;
    }

    public Command parse(String command) {

        List<String> args = Arrays.asList(command.split(" "));
        validator.validate(command);

        switch (args.get(0)) {
            case Commands.GET ->    { return parseGet(args); }
            case Commands.CREATE -> { return parseCreate(command); }
            case Commands.UPDATE -> { return parseUpdate(command, args); }
            case Commands.DELETE -> { return parseDelete(args); }

            default -> throw new IllegalArgumentException("Неверная команда");
        }
    }

    private Command parseGet(List<String> args) {
        return args.size() == 1
                ? new Command(CommandType.GET_ALL)
                : new Command(CommandType.GET, Integer.parseInt(args.get(1)));
    }

    private Command parseCreate(String command) {
        Person person = parsePerson(command);
        return new Command(CommandType.CREATE, person);
    }

    private Command parseUpdate(String command, List<String> args) {
        int id = Integer.parseInt(args.get(1));
        Person person = parsePerson(command);
        return new Command(CommandType.UPDATE, id, person);
    }

    private Command parseDelete(List<String> args) {
        int id = Integer.parseInt(args.get(1));
        return new Command(CommandType.DELETE, id);
    }

    private Person parsePerson(String command) {
        try {
            return objectMapper.readValue(getPersonString(command), Person.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Неверная команда");
        }
    }

    private String getPersonString(String command) {
        return command.substring(command.indexOf('{'));
    }
}
