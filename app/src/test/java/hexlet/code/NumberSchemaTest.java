package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    private Validator validator;
    private NumberSchema schema;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        schema = validator.number();
    }

    @Test
    void testEmptySchema() {
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
    }

    @Test
    void testRequired() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
    }

    @Test
    void testPositive() {
        schema.positive();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    void testRange() {
        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testMultipleConstraints() {
        schema.required().positive().range(5, 10);
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
    }

    @Test
    void testOverridingConstraints() {
        schema.range(5, 10).range(1, 3);
        assertTrue(schema.isValid(2));
        assertFalse(schema.isValid(5));
    }
}
