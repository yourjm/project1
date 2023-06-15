package com.zor07;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Loader {

    private static final String FILE_NAME = "map.properties";


    public void saveToFile(Map<Integer, String> map) {
        try (FileOutputStream fos = new FileOutputStream(getFileOrCreateIfNotExists())) {
            Properties properties = new Properties();
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                properties.put(entry.getKey().toString(), entry.getValue());
            }
            properties.store(fos, null);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка сохранения данных в файл: " + e.getMessage());
        }
    }

    public Map<Integer, String> loadFromFile() {
        try (FileInputStream fis = new FileInputStream(getFileOrCreateIfNotExists())) {
            Map<Integer, String> map = new HashMap<>();
            Properties properties = new Properties();
            properties.load(fis);

            for (String key : properties.stringPropertyNames()) {
                map.put(Integer.parseInt(key), properties.get(key).toString());
            }
            return map;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения данных из файла: " + e.getMessage());
        }
    }

    private File getFileOrCreateIfNotExists() throws IOException {
        String path = System.getProperty("user.home") + File.separator + FILE_NAME;
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

}
