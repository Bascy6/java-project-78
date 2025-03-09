package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<MapSchema> {
    private int requiredSize = -1;
    private Map<String, BaseSchema<?>> schemas;

    @Override
    public MapSchema required() {
        return super.required();
    }

    public MapSchema sizeof(int size) {
        this.requiredSize = size;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> shapeSchemas) {
        this.schemas = shapeSchemas;
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (isRequired && value == null) {
            return false;
        }
        if (value == null) {
            return true;
        }
        if (!(value instanceof Map)) {
            return false;
        }
        Map<?, ?> map = (Map<?, ?>) value;
        if (requiredSize != -1 && map.size() != requiredSize) {
            return false;
        }
        if (schemas != null) {
            for (Map.Entry<String, BaseSchema<?>> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                if (!map.containsKey(key) || !schema.isValid(map.get(key))) {
                    return false;
                }
            }
        }
        return true;
    }
}
