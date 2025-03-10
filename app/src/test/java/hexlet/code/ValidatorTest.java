package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    private Validator validator;
    private StringSchema schema;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        schema = validator.string();
    }

    @Test
    void testEmptySchema() {
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
    }

    @Test
    void testRequired() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void testMinLength() {
        schema.minLength(5);
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid("test"));
        assertTrue(schema.isValid("hello"));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void testContains() {
        schema.contains("hex");
        assertTrue(schema.isValid("hexlet"));
        assertTrue(schema.isValid("hexagon"));
        assertFalse(schema.isValid("example"));
    }

    @Test
    void testOverridingConstraints() {
        schema.minLength(10).minLength(4);
        assertTrue(schema.isValid("Hexlet"));
    }
}
