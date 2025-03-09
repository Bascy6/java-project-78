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
}
