package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<NumberSchema> {
    public NumberSchema positive() {
        addCondition(value -> ((Integer) value) > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCondition(value -> ((Integer) value) >= min && ((Integer) value) <= max);
        return this;
    }

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }
}
