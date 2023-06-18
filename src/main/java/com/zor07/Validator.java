package com.zor07;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zor07.model.Commands;
import com.zor07.model.Person;

import java.util.Arrays;
import java.util.List;

public class Validator {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void validate(String command) {
        if (command == null|| command.isEmpty()) {
            throwIllegalCommandException();
        }

        List<String> args = Arrays.asList(command.split(" "));
        if (args.isEmpty()) {
            throwIllegalCommandException();
        }

        switch (args.get(0)) {
            case Commands.GET -> validateGetCommand(args);
            case Commands.CREATE -> validateCreateCommand(command, args);
            case Commands.UPDATE -> validateUpdateCommand(command, args);
            case Commands.DELETE -> validateDeleteCommand(args);
            default -> throwIllegalCommandException();
        }
    }

    private void validateGetCommand(List<String> args) {
        if (args.size() > 2) {
            throwIllegalCommandException();
        }

        if (!Commands.GET.equals(args.get(0))) {
            throwIllegalCommandException();
        }

        if (args.size() > 1 && isNotInt(args.get(1))) {
            throwIllegalCommandException();
        }
    }

    private void validateCreateCommand(String command, List<String> args) {
        if (args.size() < 2) {
            throwIllegalCommandException();
        }
        if (!Commands.CREATE.equals(args.get(0))) {
            throwIllegalCommandException();
        }
        try {
            objectMapper.readValue(getPersonString(command), Person.class);
        } catch (JsonProcessingException e) {
            throwIllegalCommandException();
        }
    }

    private void validateUpdateCommand(String command, List<String> args) {
        if (args.size() < 3) {
            throwIllegalCommandException();
        }
        if (!Commands.UPDATE.equals(args.get(0))) {
            throwIllegalCommandException();
        }
        if (isNotInt(args.get(1))) {
            throwIllegalCommandException();
        }
        try {
            objectMapper.readValue(getPersonString(command), Person.class);
        } catch (JsonProcessingException e) {
            throwIllegalCommandException();
        }
    }

    private void validateDeleteCommand(List<String> args) {
        if (args.size() != 2) {
            throwIllegalCommandException();
        }

        if (!Commands.DELETE.equals(args.get(0))) {
            throwIllegalCommandException();
        }

        if (isNotInt(args.get(1))) {
            throwIllegalCommandException();
        }
    }

    private boolean isNotInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    private void throwIllegalCommandException() {
        throw new IllegalArgumentException("Введена невалидная команда");
    }

    private String getPersonString(String command) {
        return command.substring(command.indexOf('{'));
    }
}
