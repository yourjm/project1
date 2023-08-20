package com.zor07;

import com.zor07.dao.PersonDAO;
import com.zor07.model.Command;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Validator validator = new Validator();
        Parser parser = new Parser(validator);

        PersonDAO storage = new PersonDAO();
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
