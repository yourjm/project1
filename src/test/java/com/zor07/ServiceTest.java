package com.zor07;

import com.zor07.model.Command;
import com.zor07.model.CommandType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceTest {


    @Test
    void shouldCreateNewValueWhenExecutingCreateCommand() {
        //given
        Command command = new Command(CommandType.CREATE, "value");
        Storage storage = new Storage(new HashMap<>());
        Service service = new Service(storage);

        //when
        service.execute(command);

        //then
        Map<Integer, String> result = storage.getMap();
        assertEquals(1, result.size());
        assertEquals("value", result.get(1));
    }

    @Test
    void shouldUpdateValueWhenExecutingUpdateCommand() {
        //given
        Storage storage = new Storage(Map.of(1, "value1"));
        Command command = new Command(CommandType.UPDATE, 1, "new value");
        Service service = new Service(storage);

        //when
        service.execute(command);

        //then
        Map<Integer, String> result = storage.getMap();
        assertEquals(1, result.size());
        assertEquals("new value", result.get(1));
    }

    @Test
    void shouldDeleteValueWhenExecutingDeleteCommand() {
        //given
        Storage storage = new Storage(Map.of(1, "value1"));
        Command command = new Command(CommandType.DELETE, 1);
        Service service = new Service(storage);

        //when
        service.execute(command);

        //then
        assertTrue(storage.getMap().isEmpty());
    }

}
