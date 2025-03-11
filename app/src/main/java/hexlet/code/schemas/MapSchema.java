package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<MapSchema> {
    private Integer size;
    private Map<String, BaseSchema<?>> shapeSchemas;

    public MapSchema sizeof(int newSize) {
        this.size = newSize;
        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        this.shapeSchemas = new HashMap<>(schemas);
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return !required;
        }
        if (!(value instanceof Map)) {
            return false;
        }
        Map<?, ?> mapValue = (Map<?, ?>) value;
        if (size != null && mapValue.size() != size) {
            return false;
        }
        if (shapeSchemas != null) {
            for (Map.Entry<String, BaseSchema<?>> entry : shapeSchemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                if (!mapValue.containsKey(key)) {
                    return false;
                }
                Object keyValue = mapValue.get(key);
                if (!schema.isValid(keyValue)) {
                    return false;
                }
            }
        }
        return true;
    }
}
