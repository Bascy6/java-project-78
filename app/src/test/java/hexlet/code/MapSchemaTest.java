package hexlet.code.schemas;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import hexlet.code.Validator;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {

    @Test
    public void testRequired() {
        MapSchema mapSchema = new MapSchema().required();

        assertTrue(mapSchema.isValid(new HashMap<>()));
        assertFalse(mapSchema.isValid(null));
    }

    @Test
    public void testSizeof() {
        MapSchema mapSchema = new MapSchema().sizeof(2);

        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        assertTrue(mapSchema.isValid(map));

        map.put("key3", "value3");
        assertFalse(mapSchema.isValid(map));
    }

    @Test
    public void testShape() {
        MapSchema mapSchema = new MapSchema().shape(Map.of(
                "name", Validator.string().required().minLength(3),
                "age", Validator.number().required().range(18, 99)
        ));

        Map<String, Object> validData = new HashMap<>();
        validData.put("name", "John");
        validData.put("age", 25);

        assertTrue(mapSchema.isValid(validData));

        Map<String, Object> invalidData = new HashMap<>();
        invalidData.put("name", "Jo");
        invalidData.put("age", 17);

        assertFalse(mapSchema.isValid(invalidData));
    }
}
