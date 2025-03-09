package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringSchemaTest {

    @Test
    public void testRequired() {
        StringSchema schema1 = new StringSchema();
        assertFalse(schema1.required().isValid(null));
        assertFalse(schema1.required().isValid(""));

        StringSchema schema2 = new StringSchema();
        assertTrue(schema2.isValid(null));
        assertTrue(schema2.isValid(""));
    }

    @Test
    public void testMinLength() {
        StringSchema schema = new StringSchema();
        schema.minLength(3);
        assertTrue(schema.isValid("abc"));
        assertFalse(schema.isValid("ab"));
    }

    @Test
    public void testContains() {
        StringSchema schema = new StringSchema();
        schema.contains("test");
        assertTrue(schema.isValid("This is a test string"));
        assertFalse(schema.isValid("This is a string"));
    }
}
