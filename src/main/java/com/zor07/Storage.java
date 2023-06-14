package com.zor07;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {


    private final HashMap<Integer, String> map;
    private final AtomicInteger id;

    public Storage(Map<Integer, String> map) {
        this.map = new HashMap<>(map);
        Integer lastId = map.keySet()
                .stream()
                .max(Integer::compareTo)
                .orElse(1);
        id = new AtomicInteger(lastId);
    }

    public Collection<String> getAll() {
        return map.values();
    }

    public String get(Integer id) {
        return map.get(id);
    }

    public Integer create(String value) {
        id.incrementAndGet();
        map.put(id.get(), value);
        return id.get();
    }

    public void update(Integer id, String newValue) {
        map.replace(id, newValue);
    }

    public void delete(Integer id) {
        map.remove(id);
    }

}
