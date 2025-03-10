package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
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
    void testMultipleConstraints() {
        schema.required().minLength(5).contains("hex");
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid("test"));
        assertTrue(schema.isValid("hexagon"));
        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid("hex"));
        assertTrue(schema.isValid("hexagonhex"));
    }

    @Test
    void testOverridingConstraints() {
        schema.minLength(10).minLength(4);
        assertTrue(schema.isValid("Hexlet"));
    }

    @Test
    void testChainingMethods() {
        schema.required().minLength(5).contains("hex");
        StringSchema schemaChain = validator.string().required().minLength(5).contains("hex");
        assertTrue(schemaChain.isValid("hexagon"));
    }

    @Test
    void testNonStringValues() {
        schema.required();
        assertFalse(schema.isValid(123));
        assertFalse(schema.isValid(3.14));
        assertFalse(schema.isValid(new Object()));
        assertFalse(schema.isValid(new int[]{1, 2, 3}));
        assertTrue(schema.isValid("hexlet"));
    }
}
