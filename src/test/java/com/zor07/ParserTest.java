package com.zor07;

import com.zor07.model.Command;
import com.zor07.model.CommandType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {


    private final Validator validator = new Validator();
    private final Parser parser = new Parser(validator);


    @Test
    void parseGetAllTest() {
        String commandString = "GET";
        Command result = parser.parse(commandString);

        assertEquals(CommandType.GET_ALL, result.getType());
        assertNull(result.getValue());
        assertNull(result.getId());
    }

    @Test
    void parseGetByIdTest() {
        String commandString = "GET 123";
        Command result = parser.parse(commandString);

        assertEquals(CommandType.GET, result.getType());
        assertNull(result.getValue());
        assertEquals(123, result.getId());
    }

    @Test
    void parseCreateTest() {
        String commandString = "CREATE some awesome string";
        Command result = parser.parse(commandString);

        assertEquals(CommandType.CREATE, result.getType());
        assertEquals("some awesome string", result.getValue());
        assertNull(result.getId());
    }

    @Test
    void parseUpdateTest() {
        String commandString = "UPDATE 12 some awesome string";
        Command result = parser.parse(commandString);

        assertEquals(CommandType.UPDATE, result.getType());
        assertEquals("some awesome string", result.getValue());
        assertEquals(12, result.getId());
    }

    @Test
    void parseDeleteTest() {
        String commandString = "DELETE 123";
        Command result = parser.parse(commandString);

        assertEquals(CommandType.DELETE, result.getType());
        assertNull(result.getValue());
        assertEquals(123, result.getId());
    }
}
