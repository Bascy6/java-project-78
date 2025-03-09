package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringSchemaTest {

    @Test
    public void testDefaultSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("hexlet"));
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
    public void testMinLength() {
        Validator v = new Validator();
        StringSchema schema = v.string().minLength(5);

        assertFalse(schema.isValid("hex"));
        assertTrue(schema.isValid("hexlet"));
        assertTrue(schema.isValid("hello world"));
    }

    @Test
    public void testContains() {
        Validator v = new Validator();
        StringSchema schema = v.string().contains("hex");

        assertFalse(schema.isValid("hello"));
        assertTrue(schema.isValid("hexlet"));
        assertTrue(schema.isValid("what does the fox say? hex"));
    }

    @Test
    public void testCombinedRules() {
        Validator v = new Validator();
        StringSchema schema = v.string()
                .required()
                .minLength(5)
                .contains("hex");

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid("hex"));
        assertFalse(schema.isValid("hello"));
        assertTrue(schema.isValid("hexlet"));
        assertTrue(schema.isValid("what does the fox say? hex"));
    }

    @Test
    public void testOverridingRules() {
        Validator v = new Validator();
        StringSchema schema = v.string()
                .minLength(10)
                .minLength(4);

        assertTrue(schema.isValid("Hexlet"));
        assertFalse(schema.isValid("Hex"));
    }
}
