package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    private Validator validator;
    private StringSchema stringSchema;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        stringSchema = validator.string();
    }

    @Test
    void testEmptySchema() {
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));
    }

    @Test
    void testRequired() {
        stringSchema.required();
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid("hexlet"));
    }

    @Test
    void testMinLength() {
        stringSchema.minLength(5);
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid("test"));
        assertTrue(stringSchema.isValid("hello"));
        assertTrue(stringSchema.isValid("hexlet"));
    }

    @Test
    void testContains() {
        stringSchema.contains("hex");
        assertTrue(stringSchema.isValid("hexlet"));
        assertTrue(stringSchema.isValid("hexagon"));
        assertFalse(stringSchema.isValid("example"));
    }

    @Test
    void testMultipleConstraints() {
        stringSchema.required().minLength(5).contains("hex");
        assertTrue(stringSchema.isValid("hexagon"));
    }

    @Test
    void testOverridingConstraints() {
        stringSchema.minLength(10).minLength(4);
        assertTrue(stringSchema.isValid("Hexlet"));
    }

    @Test
    void testShape() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required().contains("ya"));
        schemas.put("lastName", validator.string().required().contains("ov"));
        MapSchema mapSchema = validator.map().shape(schemas);

        Map<String, String> person1 = new HashMap<>();
        person1.put("firstName", "yana");
        person1.put("lastName", "Petrov");
        assertTrue(mapSchema.isValid(person1));

        Map<String, String> person5 = new HashMap<>();
        person5.put("firstName", "yana");
        person5.put("lastName", "Petrov");
        assertTrue(mapSchema.isValid(person5));
    }
}
