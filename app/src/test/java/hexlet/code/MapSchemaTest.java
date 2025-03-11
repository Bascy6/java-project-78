package hexlet.code;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {

    @Test
    public void testShapeValidationWithStringKeys() {
        Validator validator = new Validator();
        MapSchema<String> schema = validator.map();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));
        schemas.put("age", validator.number().positive());

        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        human1.put("age", 30);
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        human2.put("age", 30);
        assertFalse(schema.isValid(human2));
    }

    @Test
    public void testShapeValidationWithIntegerKeys() {
        Validator validator = new Validator();
        MapSchema<Integer> schema = validator.map();

        Map<Integer, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put(1, validator.string().required());
        schemas.put(2, validator.number().positive());

        schema.shape(schemas);

        Map<Integer, Object> data1 = new HashMap<>();
        data1.put(1, "Hello");
        data1.put(2, 42);
        assertTrue(schema.isValid(data1));

        Map<Integer, Object> data2 = new HashMap<>();
        data2.put(1, "");
        data2.put(2, -5);
        assertFalse(schema.isValid(data2));
    }
}
