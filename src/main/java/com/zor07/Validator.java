package com.zor07;

import com.zor07.model.Commands;

import java.util.List;

public class Validator {

    public void validate(List<String> args) {

        if (args ==null || args.isEmpty()) {
            throwIllegalCommandException();
        }

        switch (args.get(0)) {
            case Commands.GET -> validateGetCommand(args);
            case Commands.CREATE -> validateCreateCommand(args);
            case Commands.UPDATE -> validateUpdateCommand(args);
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

    private void validateCreateCommand(List<String> args) {
        if (args.size() < 2) {
            throwIllegalCommandException();
        }
        if (!Commands.CREATE.equals(args.get(0))) {
            throwIllegalCommandException();
        }
    }

    private void validateUpdateCommand(List<String> args) {
        if (args.size() < 3) {
            throwIllegalCommandException();
        }
        if (!Commands.UPDATE.equals(args.get(0))) {
            throwIllegalCommandException();
        }
        if (isNotInt(args.get(1))) {
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


}
