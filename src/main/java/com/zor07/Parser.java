package com.zor07;

import com.zor07.model.Command;
import com.zor07.model.CommandType;

import java.util.Arrays;
import java.util.List;

public class Parser {


    private final Validator validator;

    public Parser(Validator validator) {
        this.validator = validator;
    }

    public Command parse(String command) {

        List<String> args = Arrays.asList(command.split(" "));
        validator.validate(args);

        switch (args.get(0)) {
            case Commands.GET ->    { return parseGet(args); }
            case Commands.CREATE -> { return parseCreate(command, args); }
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

    private Command parseCreate(String command, List<String> args) {
        String toReplace = String.format("%s ", args.get(0));
        String value = command.replaceFirst(toReplace, "");
        return new Command(CommandType.CREATE, value);
    }

    private Command parseUpdate(String command, List<String> args) {
        int id = Integer.parseInt(args.get(1));
        String toReplace = String.format("%s %s ", args.get(0), args.get(1));
        String value = command.replaceFirst(toReplace, "");
        return new Command(CommandType.UPDATE, id, value);
    }

    private Command parseDelete(List<String> args) {
        int id = Integer.parseInt(args.get(1));
        return new Command(CommandType.DELETE, id);
    }
}
