package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema<MapSchema> {
    private int requiredSize = -1;

    @Override
    public boolean isValid(Object value) {
        if (!super.isValid(value)) {
            return false;
        }
        if (value == null) {
            return !isRequired;
        }
        if (!(value instanceof Map)) {
            return false;
        }
        Map<?, ?> map = (Map<?, ?>) value;
        if (requiredSize != -1 && map.size() != requiredSize) {
            return false;
        }
        return true;
    }

    public MapSchema sizeof(int size) {
        this.requiredSize = size;
        return this;
    }
}
