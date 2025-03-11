package hexlet.code;

import hexlet.code.schemas.StringSchema;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StringSchemaTest {

    @Test
    public void testRequired() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("Hello"));

        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("Hello"));
    }

    @Test
    public void testMinLength() {
        Validator validator = new Validator();
        StringSchema schema = validator.string().minLength(3);

        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid("ab"));
        assertTrue(schema.isValid("abc"));
        assertTrue(schema.isValid("abcd"));

        schema.required();
        assertFalse(schema.isValid("ab"));
        assertTrue(schema.isValid("abc"));
    }

    @Test
    public void testContains() {
        Validator validator = new Validator();
        StringSchema schema = validator.string().contains("test");

        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid("tes"));
        assertTrue(schema.isValid("test"));
        assertTrue(schema.isValid("test123"));
        assertTrue(schema.isValid("123test456"));

        schema.required();
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid("tes"));
        assertTrue(schema.isValid("test"));
    }

    @Test
    public void testCombinedConditions() {
        Validator validator = new Validator();
        StringSchema schema = validator.string()
                .required()
                .minLength(5)
                .contains("hex");

        assertFalse(schema.isValid("hex"));
        assertFalse(schema.isValid("hex1"));
        assertTrue(schema.isValid("hex12"));
        assertFalse(schema.isValid("he1234"));
        assertTrue(schema.isValid("hexhex"));
    }

    @Test
    public void testOverriding() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();

        schema.minLength(10);
        assertFalse(schema.isValid("123456789"));
        schema.minLength(5);
        assertTrue(schema.isValid("12345"));

        schema.contains("a").contains("b");
        assertFalse(schema.isValid("a"));
        assertFalse(schema.isValid("ab"));
    }

    @Test
    public void testNonStringValues() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();

        assertFalse(schema.isValid(123));
        assertFalse(schema.isValid(new Object()));

        schema.required();
        assertFalse(schema.isValid(123));
    }
}
