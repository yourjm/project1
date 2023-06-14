package com.zor07;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatorTest {

    private final Validator validator = new Validator();


    @Test
    void validateGetAllTest() {
        List<String> args = List.of("GET");
        assertDoesNotThrow(() -> validator.validate(args));
    }

    @Test
    void validateGetByIdTest() {
        List<String> args = List.of("GET", "123");
        assertDoesNotThrow(() -> validator.validate(args));
    }

    @Test
    void validateCreateTest() {
        List<String> args = List.of("CREATE", "Some", "String", "for", "test");
        assertDoesNotThrow(() -> validator.validate(args));
    }

    @Test
    void validateUpdateTest() {
        List<String> args = List.of("CREATE", "12", "Some", "String", "for", "test");
        assertDoesNotThrow(() -> validator.validate(args));
    }

    @Test
    void validateDeleteTest() {
        List<String> args = List.of("DELETE", "12");
        assertDoesNotThrow(() -> validator.validate(args));
    }

    @Test
    void shouldThrowExceptionWhenValidatingEmptyList() {
        List<String> args = Collections.emptyList();
        Exception ex = assertThrows(
            IllegalArgumentException.class,
            () -> validator.validate(args)
        );

        assertEquals("Введена невалидная команда", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenValidatingNullList() {
        List<String> args = Collections.emptyList();
        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(args)
        );

        assertEquals("Введена невалидная команда", ex.getMessage());
    }

    @Test
    void validateGetShouldThrowExceptionWhenListHas3Elements() {
        List<String> args = List.of("GET", "12", "asd");
        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(args)
        );

        assertEquals("Введена невалидная команда", ex.getMessage());
    }

    @Test
    void validateGetShouldThrowExceptionWhenFirstArgIsNotGET() {
        List<String> args = List.of("12", "asd");
        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(args)
        );

        assertEquals("Введена невалидная команда", ex.getMessage());
    }

    @Test
    void validateGetShouldThrowExceptionWhenSecondArgIsNotInt() {
        List<String> args = List.of("GET", "asd");
        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(args)
        );

        assertEquals("Введена невалидная команда", ex.getMessage());
    }

    @Test
    void validateCreateShouldThrowExceptionWhenListHas1Elements() {
        List<String> args = List.of("CREATE");
        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(args)
        );

        assertEquals("Введена невалидная команда", ex.getMessage());
    }

    @Test
    void validateCreateShouldThrowExceptionWhenFirstAgrIsNotCREATE() {
        List<String> args = List.of("asd", "123");
        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(args)
        );

        assertEquals("Введена невалидная команда", ex.getMessage());
    }

    @Test
    void validateUpdateShouldThrowExceptionWhenListHas2Elements() {
        List<String> args = List.of("UPDATE", "123");
        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(args)
        );

        assertEquals("Введена невалидная команда", ex.getMessage());
    }

    @Test
    void validateUpdateShouldThrowExceptionWhenFirstAgrIsNotUPDATE() {
        List<String> args = List.of("asd", "123");
        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(args)
        );

        assertEquals("Введена невалидная команда", ex.getMessage());
    }

    @Test
    void validateUPDATEShouldThrowExceptionWhenSecondArgIsNotInt() {
        List<String> args = List.of("UPDATE", "asd", "asd");
        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(args)
        );

        assertEquals("Введена невалидная команда", ex.getMessage());
    }

    @Test
    void validateDeleteShouldThrowExceptionWhenListHas1Elements() {
        List<String> args = List.of("DELETE");
        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(args)
        );

        assertEquals("Введена невалидная команда", ex.getMessage());
    }

    @Test
    void validateDeleteShouldThrowExceptionWhenFirstAgrIsNotDELETE() {
        List<String> args = List.of("asd", "123");
        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(args)
        );

        assertEquals("Введена невалидная команда", ex.getMessage());
    }

    @Test
    void validateDeleteShouldThrowExceptionWhenSecondArgIsNotInt() {
        List<String> args = List.of("UPDATE", "asd", "asd");
        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(args)
        );

        assertEquals("Введена невалидная команда", ex.getMessage());
    }



}
