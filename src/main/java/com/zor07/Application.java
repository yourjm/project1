package com.zor07;

import com.zor07.model.Command;

import java.util.Scanner;

public class Application {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/project_db";
    private static final String USER = "user";
    private static final String PASSWORD = "pass";

    public static void main(String[] args) {

        Validator validator = new Validator();
        Parser parser = new Parser(validator);

        Storage storage = new Storage(DB_URL, USER, PASSWORD);
        Service service = new Service(storage);


        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    String line = scanner.nextLine();
                    if ("QUIT".equals(line)) {
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
