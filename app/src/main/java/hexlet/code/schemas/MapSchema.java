package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<MapSchema> {
    private Integer size;
    private Map<String, BaseSchema<?>> shapeSchemas;

    public MapSchema sizeof(int newSize) {
        this.size = newSize;
        addCondition(value -> ((Map<?, ?>) value).size() == size);
        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        this.shapeSchemas = new HashMap<>(schemas);
        addCondition(value -> {
            Map<?, ?> mapValue = (Map<?, ?>) value;
            for (Map.Entry<String, BaseSchema<?>> entry : shapeSchemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                if (!mapValue.containsKey(key) || !schema.isValid(mapValue.get(key))) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }
}
