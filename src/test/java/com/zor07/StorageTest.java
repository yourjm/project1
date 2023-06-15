package com.zor07;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {


    @Test
    void getAllTest() {
        Map<Integer,String> map = Map.of(
                1, "value1",
                2, "value2"
        );

        Storage storage = new Storage(map);
        Map<Integer, String> result = storage.getMap();

        assertEquals(2, result.size());
        assertEquals("value1", map.get(1));
        assertEquals("value2", map.get(2));
    }

    @Test
    void getTest() {
        Map<Integer,String> map = Map.of(
                1, "value1",
                2, "value2"
        );
        Storage storage = new Storage(map);
        String result = storage.get(1);

        assertEquals("value1", result);
    }

    @Test
    void createTest() {
        Storage storage = new Storage(new HashMap<>());
        storage.create("value");

        Map<Integer, String> result = storage.getMap();

        assertEquals(1, result.size());
        assertEquals("value", result.get(1));
    }

    @Test
    void updateTest() {
        Map<Integer,String> map = Map.of(1, "value1");
        Storage storage = new Storage(map);

        storage.update(1, "new value");

        Map<Integer, String> result = storage.getMap();

        assertEquals(1, result.size());
        assertEquals("new value", result.get(1));
    }

    @Test
    void deleteTest() {
        Map<Integer,String> map = Map.of(1, "value1");
        Storage storage = new Storage(map);

        storage.delete(1);

        assertTrue(storage.getMap().isEmpty());
    }



}
