package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NumberSchemaTest {

    @Test
    public void testDefaultSchema() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
    }

    @Test
    public void testRequired() {
        Validator v = new Validator();
        StringSchema schema = v.string().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    public void testPositive() {
        Validator v = new Validator();
        NumberSchema schema = v.number().positive();

        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    public void testRange() {
        Validator v = new Validator();
        NumberSchema schema = v.number().range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    public void testCombinedRules() {
        Validator v = new Validator();
        NumberSchema schema = v.number()
                .required()
                .positive()
                .range(5, 10);

        assertFalse(schema.isValid(null)); // required
        assertFalse(schema.isValid(-5));   // positive
        assertFalse(schema.isValid(0));    // positive
        assertFalse(schema.isValid(4));    // range
        assertFalse(schema.isValid(11));   // range
        assertTrue(schema.isValid(5));     // valid
        assertTrue(schema.isValid(10));    // valid
    }
}
