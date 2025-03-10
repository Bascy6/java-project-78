package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<MapSchema> {
    private boolean isRequired = false;
    private Integer mapSize = null;
    private Map<String, BaseSchema<?>> shapeSchemas = new HashMap<>();

    @Override
    protected MapSchema getThis() {
        return this;
    }

    @Override
    public MapSchema required() {
        this.isRequired = true;
        return super.required();
    }

    @Override
    public boolean isRequired() {
        return isRequired;
    }

    public MapSchema sizeof(int size) {
        this.mapSize = size;
        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        this.shapeSchemas = new HashMap<>(schemas);
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return !isRequired;
        }
        if (!(value instanceof Map)) {
            return false;
        }
        Map<?, ?> map = (Map<?, ?>) value;

        if (isRequired && map.isEmpty()) {
            return false;
        }
        if (mapSize != null && map.size() != mapSize) {
            return false;
        }

        for (Map.Entry<String, BaseSchema<?>> entry : shapeSchemas.entrySet()) {
            String key = entry.getKey();
            BaseSchema<?> schema = entry.getValue();

            if (!map.containsKey(key)) {
                if (schema.isRequired()) {
                    return false;
                }
                continue;
            }

            Object val = map.get(key);
            if (!schema.isValid(val)) {
                return false;
            }
        }
        return true;
    }
}
