package hexlet.code.schemas;

import java.util.Map;

public class MapSchema<K> extends BaseSchema<Map<K, ?>> {
    private Integer size;
    private Map<K, BaseSchema<?>> shapeSchemas;

    public MapSchema<K> sizeof(int newSize) {
        this.size = newSize;
        return this;
    }

    public MapSchema<K> shape(Map<K, ? extends BaseSchema<?>> schemas) {
        this.shapeSchemas = (Map<K, BaseSchema<?>>) schemas;
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
            for (Map.Entry<K, BaseSchema<?>> entry : shapeSchemas.entrySet()) {
                K key = entry.getKey();
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
