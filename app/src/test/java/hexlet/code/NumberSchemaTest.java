package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NumberSchemaTest {

    @Test
    public void testRequired() {
        NumberSchema schema1 = new NumberSchema();
        assertFalse(schema1.required().isValid(null));

        NumberSchema schema2 = new NumberSchema();
        assertTrue(schema2.isValid(null));
    }

    @Test
    public void testPositive() {
        NumberSchema schema = new NumberSchema();
        assertTrue(schema.positive().isValid(10));
        assertFalse(schema.positive().isValid(-5));
        assertFalse(schema.positive().isValid(0));
    }

    @Test
    public void testRange() {
        NumberSchema schema = new NumberSchema();
        schema.range(5, 10);
        assertTrue(schema.isValid(7));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    public void testCombined() {
        NumberSchema schema = new NumberSchema();
        schema.required().positive().range(1, 10);
        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(-3));
        assertFalse(schema.isValid(15));
    }
}
