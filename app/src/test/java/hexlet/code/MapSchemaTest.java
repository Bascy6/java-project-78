package hexlet.code;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MapSchemaTest {

    @Test
    public void testDefaultSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    public void testRequired() {
        Validator v = new Validator();
        MapSchema schema = v.map().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testSizeof() {
        Validator v = new Validator();
        MapSchema schema = v.map().sizeof(2);

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testCombinedRules() {
        Validator v = new Validator();
        MapSchema schema = v.map()
                .required()
                .sizeof(2);

        assertFalse(schema.isValid(null));

        assertFalse(schema.isValid(new HashMap<>()));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testShape() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1)); // true

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2)); // false

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3)); // false

        Map<String, String> human4 = new HashMap<>();
        human4.put("firstName", "Alice");
        human4.put("lastName", "Johnson");
        assertTrue(schema.isValid(human4)); // true

        Map<String, String> human5 = new HashMap<>();
        human5.put("firstName", null);
        human5.put("lastName", "Doe");
        assertFalse(schema.isValid(human5)); // false

        Map<String, String> human6 = new HashMap<>();
        human6.put("firstName", "Bob");
        human6.put("lastName", "");
        assertFalse(schema.isValid(human6)); // false
    }
}
