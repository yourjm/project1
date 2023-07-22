package com.zor07;

import com.zor07.model.Command;
import com.zor07.model.Person;

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

    public Map<Integer, Person> getMap() {
        return storage.getMap();
    }

    private void get(Command command) {
        Person person = storage.get(command.getId());
        System.out.println(person);
    }

    private void getAll() {
        getMap().forEach((key, value) -> System.out.println(String.format("%s: %s", key, value)));
    }

    private void create(Command command) {
        Integer id = storage.create(command.getPerson());
        System.out.printf("Person saved with id = {%s}", id);
        System.out.println();
    }
    private void update(Command command) {
        boolean result = storage.update(command.getId(), command.getPerson());
        String message = result
                ? String.format("Person with id = %s updated", command.getId())
                : String.format("Person with id = %s not found", command.getId());
        System.out.println(message);
    }

    private void delete(Command command) {
        boolean result = storage.delete(command.getId());
        String message = result
                ? String.format("Person with id = %s deleted", command.getId())
                : String.format("Person with id = %s not found", command.getId());
        System.out.println(message);
    }

}
