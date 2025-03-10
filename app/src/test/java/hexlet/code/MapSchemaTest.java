package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    private Validator validator;
    private MapSchema schema;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        schema = validator.map();
    }

    @Test
    void testEmptySchema() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void testRequired() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(new HashMap<>()));
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));
    }

    @Test
    void testSizeof() {
        schema.sizeof(2);
        assertTrue(schema.isValid(null));
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    void testMultipleConstraints() {
        schema.required().sizeof(2);
        Map<String, String> data = new HashMap<>();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(data));
        data.put("key1", "value1");
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
        data.put("key3", "value3");
        assertFalse(schema.isValid(data));
    }

    @Test
    void testOverridingConstraints() {
        schema.sizeof(3).sizeof(2);
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
        data.put("key3", "value3");
        assertFalse(schema.isValid(data));
    }

    @Test
    void testShape() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required().minLength(3));
        schemas.put("lastName", validator.string().required().contains("ov"));
        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smithov");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "Jo");
        human2.put("lastName", "Smithov");
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "Brown");
        assertFalse(schema.isValid(human3));

        Map<String, String> human4 = new HashMap<>();
        human4.put("firstName", "Anna");
        human4.put("lastName", "Petrov");
        assertTrue(schema.isValid(human4));
    }

    @Test
    void testShapeWithOptionalFields() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().minLength(2));
        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "Jo");
        human2.put("lastName", "B");
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "Brown");
        assertTrue(schema.isValid(human3));
    }

    @Test
    void testShapeWithDifferentTypes() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().required().positive());
        schema.shape(schemas);

        Map<String, Object> person1 = new HashMap<>();
        person1.put("name", "John");
        person1.put("age", 30);
        assertTrue(schema.isValid(person1));

        Map<String, Object> person2 = new HashMap<>();
        person2.put("name", "John");
        person2.put("age", -10);
        assertFalse(schema.isValid(person2));

        Map<String, Object> person3 = new HashMap<>();
        person3.put("name", "John");
        person3.put("age", null);
        assertFalse(schema.isValid(person3));

        Map<String, Object> person4 = new HashMap<>();
        person4.put("name", null);
        person4.put("age", 30);
        assertFalse(schema.isValid(person4));
    }
}
