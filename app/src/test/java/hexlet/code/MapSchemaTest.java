package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MapSchemaTest {

    @Test
    public void testRequired() {
        MapSchema schema = new MapSchema();
        assertFalse(schema.required().isValid(null));
        assertTrue(schema.isValid(Map.of()));
    }

    @Test
    public void testSizeof() {
        MapSchema schema = new MapSchema();
        schema.sizeof(2);
        assertTrue(schema.isValid(Map.of("key1", "value1", "key2", "value2")));
        assertFalse(schema.isValid(Map.of("key1", "value1")));
    }

    @Test
    public void testShape() {
        MapSchema schema = new MapSchema();

        StringSchema stringSchema = new StringSchema().required().minLength(3);
        NumberSchema numberSchema = new NumberSchema().required().positive();

        Map<String, BaseSchema<?, ?>> shapeSchemas = Map.of(
                "name", stringSchema,
                "age", numberSchema
        );

        schema.shape(shapeSchemas);

        Map<String, Object> validData = Map.of(
                "name", "Alice",
                "age", 25
        );

        Map<String, Object> invalidData = Map.of(
                "name", "Bo",
                "age", -5
        );

        assertTrue(schema.isValid(validData));
        assertFalse(schema.isValid(invalidData));
    }
}
