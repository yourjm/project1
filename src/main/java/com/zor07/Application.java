package com.zor07;

import com.zor07.model.Command;

import java.util.Scanner;

public class Application {


    public static void main(String[] args) {

        Loader loader = new Loader();

        Validator validator = new Validator();
        Parser parser = new Parser(validator);

        Storage storage = new Storage(loader.loadFromFile());
        Service service = new Service(storage);


        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    String line = scanner.nextLine();
                    if ("QUIT".equals(line)) {
                        loader.saveToFile(service.getMap());
                        break;
                    }
                    Command command = parser.parse(line);
                    service.execute(command);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        }
    }



}
