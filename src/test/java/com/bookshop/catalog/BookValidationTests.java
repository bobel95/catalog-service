package com.bookshop.catalog;

import com.bookshop.catalog.domain.Book;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrect_validationSucceeds() {
        var book = new Book(
                "1234567890",
                "Title",
                "Author",
                1.0
        );

        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertTrue(violations.isEmpty());
    }

    @Test
    void whenIsbnDefinedIncorrectly_validationFails() {
        var book = new Book(
                "123",
                "Title",
                "Author",
                1.0
        );
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertEquals(violations.size(), 1);
        assertEquals(
                violations.iterator().next().getMessage(),
                "The ISBN format must be valid."
        );
    }
}
