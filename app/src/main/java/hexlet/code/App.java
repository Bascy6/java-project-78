package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Validator v = new Validator();

        MapSchema schema = v.map();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age ", v.number().positive());

        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Alice");
        human1.put("age", 25);

        System.out.println(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Bob");
        human2.put("age", -5);

        System.out.println(schema.isValid(human2));
    }
}
