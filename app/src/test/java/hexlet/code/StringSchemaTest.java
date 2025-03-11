package hexlet.code.schemas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {

    @Test
    public void testRequired() {
        StringSchema stringSchema = new StringSchema().required();

        assertTrue(stringSchema.isValid("test"));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid(null));
    }

    @Test
    public void testMinLength() {
        StringSchema stringSchema = new StringSchema().minLength(5);

        assertTrue(stringSchema.isValid("hello"));
        assertFalse(stringSchema.isValid("hi"));
    }

    @Test
    public void testContains() {
        StringSchema stringSchema = new StringSchema().contains("test");

        assertTrue(stringSchema.isValid("this is a test"));
        assertFalse(stringSchema.isValid("this is not"));
    }

    @Test
    public void testCombination() {
        StringSchema stringSchema = new StringSchema()
                .required()
                .minLength(5)
                .contains("test");

        assertTrue(stringSchema.isValid("this is a test"));
        assertFalse(stringSchema.isValid("this is"));
        assertFalse(stringSchema.isValid("test"));
        assertFalse(stringSchema.isValid(null));
    }
}
