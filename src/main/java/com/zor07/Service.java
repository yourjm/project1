package com.zor07;

import com.zor07.model.Command;

import java.util.Map;

public class Service {

    private final Storage storage;


    public Service(Storage storage) {
        this.storage = storage;
    }


    public void execute(Command command) {

        switch (command.getType()) {
            case GET -> get(command);
            case GET_ALL -> getAll();
            case CREATE -> create(command);
            case UPDATE -> update(command);
            case DELETE -> delete(command);
        }
    }

    public Map<Integer, String> getMap() {
        return storage.getMap();
    }

    private void get(Command command) {
        String value = storage.get(command.getId());
        System.out.println(value);
    }

    private void getAll() {
        getMap().forEach((key, value) -> System.out.println(String.format("%s: %s", key, value)));
    }

    private void create(Command command) {
        Integer id = storage.create(command.getValue());
        System.out.printf("String saved with id = {%s}", id);
        System.out.println();
    }
    private void update(Command command) {
        storage.update(command.getId(), command.getValue());
        System.out.printf("String with id = %s updated", command.getId());
        System.out.println();
    }

    private void delete(Command command) {
        storage.delete(command.getId());
        System.out.printf("String with id = %s deleted", command.getId());
        System.out.println();
    }

}
