package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class NumberSchemaTest {

    @Test
    public void testRequired() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(5));
    }

    @Test
    public void testPositive() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number().positive();

        assertTrue(schema.isValid(null)); // До вызова required()
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(0));

        schema.required();

        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(0));
    }

    @Test
    public void testRange() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number().range(5, 10);

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));

        schema.required();

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    public void testCombinedConditions() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number()
                .required()
                .positive()
                .range(5, 10);

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    public void testNonIntegerValues() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number();

        assertTrue(schema.isValid("string"));
        assertTrue(schema.isValid(new Object()));
    }
}
