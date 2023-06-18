package com.zor07;

import com.zor07.model.Command;
import com.zor07.model.CommandType;
import com.zor07.model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {


    private final Validator validator = new Validator();
    private final Parser parser = new Parser(validator);

    private static final String PERSON_JSON_STRING = "{\"name\": \"Bob\", \"age\": 19}";
    private static final Person PERSON = new Person("Bob", 19);


    @Test
    void parseGetAllTest() {
        String commandString = "GET";
        Command result = parser.parse(commandString);

        assertEquals(CommandType.GET_ALL, result.getType());
        assertNull(result.getPerson());
        assertNull(result.getId());
    }

    @Test
    void parseGetByIdTest() {
        String commandString = "GET 123";
        Command result = parser.parse(commandString);

        assertEquals(CommandType.GET, result.getType());
        assertNull(result.getPerson());
        assertEquals(123, result.getId());
    }

    @Test
    void parseCreateTest() {
        String commandString = "CREATE " + PERSON_JSON_STRING;
        Command result = parser.parse(commandString);

        assertEquals(CommandType.CREATE, result.getType());
        assertEquals(PERSON, result.getPerson());
        assertNull(result.getId());
    }

    @Test
    void parseUpdateTest() {
        String commandString = "UPDATE 12 " + PERSON_JSON_STRING;
        Command result = parser.parse(commandString);

        assertEquals(CommandType.UPDATE, result.getType());
        assertEquals(PERSON, result.getPerson());
        assertEquals(12, result.getId());
    }

    @Test
    void parseDeleteTest() {
        String commandString = "DELETE 123";
        Command result = parser.parse(commandString);

        assertEquals(CommandType.DELETE, result.getType());
        assertNull(result.getPerson());
        assertEquals(123, result.getId());
    }
}
