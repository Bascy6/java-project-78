package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema<T> extends BaseSchema<Map> {
    private final String shape = "shape";
    private final String sizeOfMap = "size";

    public MapSchema required() {
        Predicate<Map> requiredPredicate = e -> !Objects.isNull(e) && (e instanceof HashMap);
        this.addCheck(isRequired, requiredPredicate);
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Map> sizeOfPredicate = e -> e.size() == size;
        this.addCheck(sizeOfMap, sizeOfPredicate);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addCheck(shape,
                map -> {
                    return schemas.entrySet().stream().allMatch(e -> {
                        var k = map.get(e.getKey());
                        var schema = e.getValue();
                        return schema.isValid((T) k);
                    });
                });
        return this;
    }
}
